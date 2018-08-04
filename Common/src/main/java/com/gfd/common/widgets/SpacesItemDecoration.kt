package com.gfd.common.widgets

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter


/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 15:17
 * @Email：878749089@qq.com
 * @descriptio：Grid分割线，最左和左右都有
 */
class SpacesItemDecoration private constructor(private val itemSplitMarginEven: Int, private val itemSplitMarginLarge: Int, private val itemSplitMarginSmall: Int, private val verticalSpacing: Int, color: Int) : RecyclerView.ItemDecoration() {

    private val mPaint: Paint

    init {
        mPaint = Paint()
        mPaint.setColor(color)
    }

    /**
     * {@inheritDoc}
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val adapter = parent.adapter

        val lRecyclerViewAdapter: LRecyclerViewAdapter
        if (adapter is LRecyclerViewAdapter) {
            lRecyclerViewAdapter = adapter
        } else {
            throw RuntimeException("the adapter must be LRecyclerViewAdapter")
        }

        drawHorizontal(c, parent, lRecyclerViewAdapter)
        drawVertical(c, parent, lRecyclerViewAdapter)
    }

    fun drawHorizontal(c: Canvas, parent: RecyclerView, adapter: LRecyclerViewAdapter) {
        val count = parent.childCount

        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            val top = child.bottom
            val bottom = top + verticalSpacing

            val left = child.left
            val right = child.right

            val position = parent.getChildAdapterPosition(child)

            c.save()

            if (adapter.isRefreshHeader(position) || adapter.isHeader(position) || adapter.isFooter(position)) {
                c.drawRect(0f, 0f, 0f, 0f, mPaint)
            } else {
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }

            c.restore()
        }
    }

    fun drawVertical(c: Canvas, parent: RecyclerView, adapter: LRecyclerViewAdapter) {
        val count = parent.childCount

        for (i in 0 until count) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.top
            //final int bottom = child.getBottom() + params.bottomMargin;
            val bottom = child.bottom + verticalSpacing //这里使用verticalSpacing 代替 params.bottomMargin
            val left = child.right + params.rightMargin
            val right = left + itemSplitMarginEven * 2

            val position = parent.getChildAdapterPosition(child)

            c.save()

            if (adapter.isRefreshHeader(position) || adapter.isHeader(position) || adapter.isFooter(position)) {
                c.drawRect(0f, 0f, 0f, 0f, mPaint)
            } else {
                c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }

            c.restore()
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter

        val lRecyclerViewAdapter: LRecyclerViewAdapter
        if (adapter is LRecyclerViewAdapter) {
            lRecyclerViewAdapter = adapter
        } else {
            throw RuntimeException("the adapter must be LRecyclerViewAdapter")
        }

        val layoutParams = view.getLayoutParams() as RecyclerView.LayoutParams
        val itemPosition = layoutParams.viewLayoutPosition
        val childCount = parent.adapter!!.itemCount

        val spanLookup = getSpanLookup(view, parent)
        applyItemHorizontalOffsets(spanLookup, itemPosition, outRect)
        applyItemVerticalOffsets(outRect, itemPosition, childCount, spanLookup.spanCount, spanLookup, lRecyclerViewAdapter)
    }

    protected fun getSpanLookup(view: View, parent: RecyclerView): com.gfd.common.widgets.SpanLookup {
        val layoutManager = parent.layoutManager
        return if (layoutManager is GridLayoutManager) {
            SpanLookupFactory.gridLayoutSpanLookup((layoutManager as GridLayoutManager?)!!)
        } else SpanLookupFactory.singleSpan()
    }

    private fun applyItemVerticalOffsets(outRect: Rect, itemPosition: Int, childCount: Int, spanCount: Int, spanLookup: com.gfd.common.widgets.SpanLookup, adapter: LRecyclerViewAdapter) {
        outRect.top = getItemTopSpacing(spanLookup, verticalSpacing, itemPosition, spanCount, childCount, adapter)
        outRect.bottom = getItemBottomSpacing(spanLookup, verticalSpacing, itemPosition, childCount, adapter)
    }

    private fun applyItemHorizontalOffsets(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int, offsets: Rect) {
        if (itemIsFullSpan(spanLookup, itemPosition)) {
            offsets.left = 0
            offsets.right = 0
            return
        }

        if (itemStartsAtTheLeftEdge(spanLookup, itemPosition)) {
            offsets.left = itemSplitMarginLarge * 2
            offsets.right = itemSplitMarginLarge
            return
        }

        if (itemEndsAtTheRightEdge(spanLookup, itemPosition)) {
            offsets.left = itemSplitMarginLarge
            offsets.right = itemSplitMarginLarge * 2
            return
        }

        if (itemIsNextToAnItemThatStartsOnTheLeftEdge(spanLookup, itemPosition)) {
            offsets.left = itemSplitMarginSmall
        } else {
            offsets.left = itemSplitMarginEven
        }

        if (itemIsNextToAnItemThatEndsOnTheRightEdge(spanLookup, itemPosition)) {
            offsets.right = itemSplitMarginSmall
        } else {
            offsets.right = itemSplitMarginEven
        }
    }

    companion object {

        fun newInstance(horizontalSpacing: Int, verticalSpacing: Int, spanCount: Int, color: Int): SpacesItemDecoration {
            val maxNumberOfSpaces = spanCount - 1
            val totalSpaceToSplitBetweenItems = maxNumberOfSpaces * horizontalSpacing

            val itemSplitMarginEven = (0.5f * horizontalSpacing).toInt()
            val itemSplitMarginLarge = totalSpaceToSplitBetweenItems / spanCount
            val itemSplitMarginSmall = horizontalSpacing - itemSplitMarginLarge

            return SpacesItemDecoration(itemSplitMarginEven, itemSplitMarginLarge, itemSplitMarginSmall, verticalSpacing, color)
        }

        private fun itemIsNextToAnItemThatStartsOnTheLeftEdge(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int): Boolean {
            return !itemStartsAtTheLeftEdge(spanLookup, itemPosition) && itemStartsAtTheLeftEdge(spanLookup, itemPosition - 1)
        }

        private fun itemIsNextToAnItemThatEndsOnTheRightEdge(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int): Boolean {
            return !itemEndsAtTheRightEdge(spanLookup, itemPosition) && itemEndsAtTheRightEdge(spanLookup, itemPosition + 1)
        }

        private fun itemIsFullSpan(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int): Boolean {
            return itemStartsAtTheLeftEdge(spanLookup, itemPosition) && itemEndsAtTheRightEdge(spanLookup, itemPosition)
        }

        private fun itemStartsAtTheLeftEdge(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int): Boolean {
            return spanLookup.getSpanIndex(itemPosition) == 0
        }

        private fun itemEndsAtTheRightEdge(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int): Boolean {
            return spanLookup.getSpanIndex(itemPosition) + spanLookup.getSpanSize(itemPosition) == spanLookup.spanCount
        }

        private fun getItemTopSpacing(spanLookup: com.gfd.common.widgets.SpanLookup, verticalSpacing: Int, itemPosition: Int, spanCount: Int, childCount: Int, adapter: LRecyclerViewAdapter): Int {
            return if (adapter.isHeader(itemPosition) || adapter.isRefreshHeader(itemPosition) || adapter.isFooter(itemPosition)) {
                0
            } else {
                if (itemIsOnTheTopRow(spanLookup, itemPosition, spanCount, childCount)) {
                    0
                } else {
                    (.5f * verticalSpacing).toInt()
                }
            }

        }

        private fun itemIsOnTheTopRow(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int, spanCount: Int, childCount: Int): Boolean {
            var latestCheckedPosition = 0
            for (i in 0 until childCount) {
                latestCheckedPosition = i
                val spanEndIndex = spanLookup.getSpanIndex(i) + spanLookup.getSpanSize(i) - 1
                if (spanEndIndex == spanCount - 1) {
                    break
                }
            }
            return itemPosition <= latestCheckedPosition
        }

        private fun getItemBottomSpacing(spanLookup: com.gfd.common.widgets.SpanLookup, verticalSpacing: Int, itemPosition: Int, childCount: Int, adapter: LRecyclerViewAdapter): Int {

            return if (adapter.isHeader(itemPosition) || adapter.isRefreshHeader(itemPosition) || adapter.isFooter(itemPosition)) {
                0
            } else {
                if (itemIsOnTheBottomRow(spanLookup, itemPosition, childCount)) {
                    0
                } else {
                    (.5f * verticalSpacing).toInt()
                }
            }

        }

        private fun itemIsOnTheBottomRow(spanLookup: com.gfd.common.widgets.SpanLookup, itemPosition: Int, childCount: Int): Boolean {
            var latestCheckedPosition = 0
            for (i in childCount - 1 downTo 0) {
                latestCheckedPosition = i
                val spanIndex = spanLookup.getSpanIndex(i)
                if (spanIndex == 0) {
                    break
                }
            }
            return itemPosition >= latestCheckedPosition
        }
    }

}