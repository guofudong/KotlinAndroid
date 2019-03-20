package com.gfd.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

/**
 * 自定义插件实现功能如下：
 *      1.自动区分Application和Library
 */
class CompileSwitch implements Plugin<Project> {

    //默认是app，直接运行assembleRelease的时候，等同于运行app:assembleRelease
    String compilemodule = "app"

    void apply(Project project) {
        String taskNames = project.gradle.startParameter.taskNames.toString()
        System.out.println("当前运行的Task：" + taskNames)
        String module = project.path.replace(":", "")
        System.out.println("当前的module" + module)
        AssembleTask assembleTask = getTaskInfo(project.gradle.startParameter.taskNames)

        if (assembleTask.isAssemble) {
            //获取运行的组件名字赋值给compilemodule
            fetchMainmodulename(project, assembleTask)
            System.out.println("当前运行的组件：" + compilemodule)
        }

        if (!project.hasProperty("isRunAlone")) {
            throw new RuntimeException(module + "中的gradle.properties文件中必须配置isRunAlone属性，来指定是否可以独立运行")
        }

        //对于isRunAlone==true的情况需要根据实际情况修改其值，
        // 但如果是false，则不用修改，该module作为一个lib，运行module:assembleRelease则发布aar到中央仓库
        boolean isRunAlone = Boolean.parseBoolean((project.properties.get("isRunAlone")))
        String mainmodulename = project.rootProject.property("mainmodulename")
        if (isRunAlone && assembleTask.isAssemble) {
            //对于要编译的组件和主项目，isRunAlone修改为true，其他组件都强制修改为false
            //这就意味着组件不能引用主项目，这在层级结构里面也是这么规定的
            if (module == compilemodule || module == mainmodulename) {
                isRunAlone = true
            } else {
                isRunAlone = false
            }
        }
        project.setProperty("isRunAlone", isRunAlone)

        //根据配置添加各种组件依赖，并且自动化生成组件加载代码
        if (isRunAlone) {
            project.apply plugin: 'com.android.application'
            boolean isMainModule = module == mainmodulename
            //不是app(空壳)，运行的是业务模块，是允许独立运行的
            if (module != mainmodulename) {
                project.android.sourceSets {
                    main {
                        //设置独立运行所需资源的加载位置，需提前配置好
                        manifest.srcFile 'src/main/runalone/AndroidManifest.xml'
                        java.srcDirs = ['src/main/java', 'src/main/runalone/java']
                        res.srcDirs = ['src/main/res', 'src/main/runalone/res']
                    }
                }
            }
            System.out.println("apply plugin is " + 'com.android.application')
            if (assembleTask.isAssemble && module == compilemodule) {
                //运行的是app，自动去依赖其他业务模块的jar包（jar包自动生成在componentrelease文件中）
                compileComponents(assembleTask, project)
                project.android.registerTransform(new CompileCodeTransform(project,isMainModule))
            }
        } else {
            //不能独立运行，设置为library，并且生成jar放在componentrelease文件下，以供App运行时去依赖
            project.apply plugin: 'com.android.library'
            System.out.println("apply plugin is " + 'com.android.library')
            project.afterEvaluate {
                Task assembleReleaseTask = project.tasks.findByPath("assembleRelease")
                if (assembleReleaseTask != null) {
                    assembleReleaseTask.doLast {
                        File infile = project.file("build/outputs/aar/$module-release.aar")
                        File outfile = project.file("../componentrelease")
                        File desFile = project.file("$module-release.aar")
                        project.copy {
                            from infile
                            into outfile
                            rename {
                                String fileName -> desFile.name
                            }
                        }
                        System.out.println("$module-release.aar copy success ")
                    }
                }
            }
        }

    }

    /**
     * 根据当前的task，获取要运行的组件，规则如下：
     * assembleRelease ---app
     * app:assembleRelease :app:assembleRelease ---app
     * sharecomponent:assembleRelease :sharecomponent:assembleRelease ---sharecomponent
     * @param assembleTask
     */
    private void fetchMainmodulename(Project project, AssembleTask assembleTask) {
        //根项目的gradle.properties中必须配置mainmodulename属性指定主modle
        if (!project.rootProject.hasProperty("mainmodulename")) {
            throw new RuntimeException("根项目的gradle.properties中必须配置mainmodulename属性指定主modle")
        }
        if (assembleTask.modules.size() > 0 && assembleTask.modules.get(0) != null
                && assembleTask.modules.get(0).trim().length() > 0
                && assembleTask.modules.get(0) != "all") {
            compilemodule = assembleTask.modules.get(0)
        } else {
            compilemodule = project.rootProject.property("mainmodulename")
        }
        if (compilemodule == null || compilemodule.trim().length() <= 0) {
            compilemodule = "app"
        }
    }

    private AssembleTask getTaskInfo(List<String> taskNames) {
        AssembleTask assembleTask = new AssembleTask()
        for (String task : taskNames) {
            if (task.toUpperCase().contains("ASSEMBLE")
                    || task.contains("aR")
                    || task.toUpperCase().contains("RESGUARD")) {
                if (task.toUpperCase().contains("DEBUG")) {
                    assembleTask.isDebug = true
                }
                assembleTask.isAssemble = true
                String[] strs = task.split(":")
                assembleTask.modules.add(strs.length > 1 ? strs[strs.length - 2] : "all")
                break
            }
        }
        return assembleTask
    }

    /**
     * 自动添加依赖，只在运行assemble任务的才会添加依赖，因此在开发期间组件之间是完全感知不到的，这是做到完全隔离的关键
     * 支持两种语法：module或者modulePackage:module,前者之间引用module工程，后者使用componentrelease中已经发布的aar
     * @param assembleTask
     * @param project
     */
    private void compileComponents(AssembleTask assembleTask, Project project) {
        String components
        if (assembleTask.isDebug) {
            components = (String) project.properties.get("debugComponent")
        } else {
            components = (String) project.properties.get("compileComponent")
        }

        if (components == null || components.length() == 0) {
            System.out.println("there is no add dependencies ")
            return
        }
        String[] compileComponents = components.split(",")
        if (compileComponents == null || compileComponents.length == 0) {
            System.out.println("there is no add dependencies ")
            return
        }
        for (String str : compileComponents) {
            System.out.println("comp is " + str)
            if (str.contains(":")) {
                File file = project.file("../componentrelease/" + str.split(":")[1] + "-release.aar")
                if (file.exists()) {
                    project.dependencies.add("implementation", str + "-release@aar")
                    System.out.println("add implementation dependencies : " + str + "-release@aar")
                } else {
                    throw new RuntimeException(str + "必须在componentrelease文件中生成业务模块对应的jar包")
                }
            } else {
                project.dependencies.add("implementation", project.project(':' + str))
                System.out.println("add implementation dependencies project : " + str)
            }
        }
    }

    private class AssembleTask {
        boolean isAssemble = false
        boolean isDebug = false
        List<String> modules = new ArrayList<>()
    }

}