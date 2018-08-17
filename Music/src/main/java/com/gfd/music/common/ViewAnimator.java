package com.gfd.music.common;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;

import java.lang.ref.WeakReference;

/**
 * Created by Bruce Too
 * On 7/12/16.
 * At 15:44
 */
public class ViewAnimator {

    View view;

    public ViewAnimator(View view) {
        this.view = view;
    }

    /**
     * add a view to execute animator
     *
     * @param view view
     * @return ViewAnimator
     */
    public static ViewAnimator putOn(View view) {
        return new ViewAnimator(view);
    }

    /**
     * change the view to execute animator
     *
     * @param view view
     * @return ViewAnimator
     */
    public ViewAnimator andPutOn(View view) {
        this.view = view;
        return this;
    }

    /**
     * Wait system to finish inflate view,and get its size
     *
     * @param sizeListener callback when finished
     */
    public void waitForSize(final Listeners.Size sizeListener) {

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                if (view != null) {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    if (sizeListener != null) {
                        sizeListener.onSize(ViewAnimator.this);
                    }
                }
                return false;
            }
        });
    }

    /**
     * get view's top value in global screen
     *
     * @return top value
     */
    public float getY() {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect.top;
    }

    /**
     * get view's x value = translationX + getLeft
     *
     * @return x value
     */
    public float getX() {
        return ViewCompat.getX(view);
    }

    public ViewAnimator alpha(float alpha) {
        if (view != null) {
            ViewCompat.setAlpha(view, alpha);
        }
        return this;
    }

    public ViewAnimator scaleX(float scale) {
        if (view != null) {
            ViewCompat.setScaleX(view, scale);
        }
        return this;
    }

    public ViewAnimator scaleY(float scale) {
        if (view != null) {
            ViewCompat.setScaleY(view, scale);
        }
        return this;
    }

    public ViewAnimator scale(float scale) {
        if (view != null) {
            ViewCompat.setScaleX(view, scale);
            ViewCompat.setScaleY(view, scale);
        }
        return this;
    }

    public ViewAnimator translationX(float translation) {
        if (view != null) {
            ViewCompat.setTranslationX(view, translation);
        }
        return this;
    }

    public ViewAnimator translationY(float translation) {
        if (view != null) {
            ViewCompat.setTranslationY(view, translation);
        }
        return this;
    }

    public ViewAnimator translation(float translationX, float translationY) {
        if (view != null) {
            ViewCompat.setTranslationX(view, translationX);
            ViewCompat.setTranslationY(view, translationY);
        }
        return this;
    }

    public ViewAnimator pivotX(float percent) {
        if (view != null) {
            ViewCompat.setPivotX(view, view.getWidth() * percent);
        }
        return this;
    }

    public ViewAnimator pivotY(float percent) {
        if (view != null) {
            ViewCompat.setPivotY(view, view.getHeight() * percent);
        }
        return this;
    }

    public ViewAnimator visible() {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public ViewAnimator invisible() {
        if (view != null) {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public ViewAnimator gone() {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 属性变化的动画效果
     *
     * @return
     */
    public AnimatorExecutor animate() {
        return new AnimatorExecutor(this);
    }


    static class AnimatorListener implements ViewPropertyAnimatorListener {

        AnimatorExecutor animatorExecutor;

        public AnimatorListener(AnimatorExecutor animatorExecutor) {
            this.animatorExecutor = animatorExecutor;
        }

        @Override
        public void onAnimationStart(View view) {
            AnimatorExecutor animatorExecutor = this.animatorExecutor;
            if (animatorExecutor != null && animatorExecutor.startListener != null) {
                Listeners.Start startListener = animatorExecutor.startListener;
                startListener.onStart();
            }
        }

        @Override
        public void onAnimationEnd(View view) {
            AnimatorExecutor animatorExecutor = this.animatorExecutor;
            if (animatorExecutor != null && animatorExecutor.endListener != null) {
                Listeners.End endListener = animatorExecutor.endListener;
                endListener.onEnd();
            }
        }

        @Override
        public void onAnimationCancel(View view) {
            AnimatorExecutor animatorExecutor = this.animatorExecutor;
            if (animatorExecutor != null && animatorExecutor.cancelListener != null) {
                Listeners.Cancel cancelListener = animatorExecutor.cancelListener;
                cancelListener.onCancel();
            }
        }
    }

    static class DurXAnimatorUpdate implements ViewPropertyAnimatorUpdateListener {

        WeakReference<AnimatorExecutor> reference;

        public DurXAnimatorUpdate(AnimatorExecutor animatorExecutor) {
            this.reference = new WeakReference<>(animatorExecutor);
        }

        @Override
        public void onAnimationUpdate(View view) {
            AnimatorExecutor animatorExecutor = reference.get();
            if (animatorExecutor != null && animatorExecutor.updateListener != null) {
                Listeners.Update updateListener = animatorExecutor.updateListener;
                updateListener.update();
            }
        }
    }


    /**
     * this inner class used to executor animator
     */
    public static class AnimatorExecutor {
        //but < 14 there are nothing...
        final ViewPropertyAnimatorCompat animator;
        final ViewAnimator viewAnimator;

        Listeners.Start startListener;
        Listeners.End endListener;
        Listeners.Update updateListener;
        Listeners.Cancel cancelListener;

        /**
         * Constructor of AnimatorExecutor with {@link ViewAnimator}
         *
         * @param viewAnimator ViewAnimator to execute
         */
        AnimatorExecutor(ViewAnimator viewAnimator) {
            this.animator = ViewCompat.animate(viewAnimator.view);
            this.viewAnimator = viewAnimator;
            //set listener
            this.animator.setListener(new AnimatorListener(this));
        }

        public AnimatorExecutor alpha(float alpha) {
            animator.alpha(alpha);
            return this;
        }

        public AnimatorExecutor alpha(float from, float to) {
            viewAnimator.alpha(from);
            return alpha(to);
        }

        public AnimatorExecutor scaleX(float scale) {
            animator.scaleX(scale);
            return this;
        }

        public AnimatorExecutor scaleX(float from, float to) {
            viewAnimator.scaleX(from);
            return scaleX(to);
        }

        public AnimatorExecutor scaleY(float scale) {
            animator.scaleY(scale);
            return this;
        }

        public AnimatorExecutor scaleY(float from, float to) {
            viewAnimator.scaleY(from);
            return scaleY(to);
        }

        public AnimatorExecutor scale(float scale) {
            animator.scaleX(scale);
            animator.scaleY(scale);
            return this;
        }

        public AnimatorExecutor scale(float from, float to) {
            viewAnimator.scale(from);
            return scale(to);
        }

        public AnimatorExecutor translationX(float translation) {
            animator.translationX(translation);
            return this;
        }

        public AnimatorExecutor translationX(float from, float to) {
            viewAnimator.translationX(from);
            return translationX(to);
        }

        public AnimatorExecutor translationY(float translation) {
            animator.translationY(translation);
            return this;
        }

        public AnimatorExecutor translationY(float from, float to) {
            viewAnimator.translationY(from);
            return translationY(to);
        }

        public AnimatorExecutor translation(float translationX, float translationY) {
            animator.translationX(translationX);
            animator.translationY(translationY);
            return this;
        }

        public AnimatorExecutor rotation(float rotation) {
            animator.rotation(rotation);
            return this;
        }

        public AnimatorExecutor duration(long duration) {
            animator.setDuration(duration);
            return this;
        }

        public AnimatorExecutor startDelay(long duration) {
            animator.setStartDelay(duration);
            return this;
        }

        public AnimatorExecutor interpolator(Interpolator interpolator) {
            animator.setInterpolator(interpolator);
            return this;
        }

        public AnimatorExecutor end(Listeners.End listener) {
            endListener = listener;
            return this;
        }

        public AnimatorExecutor update(Listeners.Update listener) {
            updateListener = listener;
            animator.setUpdateListener(new DurXAnimatorUpdate(this));
            return this;
        }

        public AnimatorExecutor start(Listeners.Start listener) {
            startListener = listener;
            return this;
        }

        public AnimatorExecutor cancel(Listeners.Cancel listener){
            cancelListener = listener;
            return this;
        }

        /**
         * get {@link ViewAnimator} from {@link AnimatorExecutor}
         *
         * @return ViewAnimator
         */
        public ViewAnimator pullOut() {
            return viewAnimator;
        }

        /**
         * execute view animate subsequently
         *
         * @param view view to be animated
         * @return AnimatorExecutor
         */
        public AnimatorExecutor thenAnimate(View view) {
            ViewAnimator viewAnimator = new ViewAnimator(view);
            AnimatorExecutor animatorExecutor = viewAnimator.animate();
            animatorExecutor.startDelay(animator.getStartDelay() + animator.getDuration());
            return animatorExecutor;
        }

        /**
         * execute view animate together
         *
         * @param view view to be animated
         * @return AnimatorExecutor
         */
        public AnimatorExecutor andAnimate(View view) {
            ViewAnimator viewAnimator = new ViewAnimator(view);
            AnimatorExecutor animatorExecutor = viewAnimator.animate();
            animatorExecutor.startDelay(animator.getStartDelay());
            return viewAnimator.animate();
        }
    }

    public static class Listeners {
       public interface End {
            void onEnd();
        }

        public interface Start {
            void onStart();
        }

        public interface Size {
            void onSize(ViewAnimator viewAnimator);
        }

        public interface Update {
            void update();
        }

        public interface Cancel{
            void onCancel();
        }
    }


}