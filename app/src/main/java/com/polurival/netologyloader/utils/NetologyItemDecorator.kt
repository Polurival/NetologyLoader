package com.polurival.netologyloader.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class NetologyItemDecorator(context: Context) : ItemDecoration() {

    companion object {
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    private var divider: Drawable? = null
    private val bounds = Rect()

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        divider = a.getDrawable(0)
        if (divider == null) {
            Log.w(
                TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()"
            )
        }
        a.recycle()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || divider == null) {
            return
        }
        canvas.save()
        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        // first item top decorator
        var child = parent.getChildAt(0)
        parent.getDecoratedBoundsWithMargins(child, bounds)
        var top = bounds.top
        var bottom = top + divider!!.intrinsicHeight
        divider!!.setBounds(left, top, right, bottom)
        divider!!.draw(canvas)

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            // each item bottom decorator
            child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, bounds)
            bottom = bounds.bottom + Math.round(child.translationY)
            top = bottom - divider!!.intrinsicHeight
            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(canvas)
        }
        canvas.restore()
    }
}