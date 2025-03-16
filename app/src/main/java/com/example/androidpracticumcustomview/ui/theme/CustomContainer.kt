package com.example.androidpracticumcustomview.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.example.androidpracticumcustomview.R

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
        setBackgroundColor(context.getColor(R.color.purple_200))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var totalWidth = 0
        var totalHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            try {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
                totalWidth = maxOf(totalWidth, child.measuredWidth)
                totalHeight = maxOf(totalHeight, child.measuredHeight)
            } catch (e: Exception) {
                handleChildError(i, e)
            }
        }
        val width = resolveSize(
            totalWidth + paddingLeft + paddingRight,
            widthMeasureSpec
        )

        val height = resolveSize(
            totalHeight + paddingTop + paddingBottom,
            heightMeasureSpec
        )

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            try {
                val left = paddingLeft
                val top = paddingTop
                val right = left + child.measuredWidth
                val bottom = top + child.measuredHeight
                child.layout(left, top, right, bottom)
            } catch (e: Exception) {
                handleChildError(i, e)
            }
        }
    }

    override fun addView(child: View) {
        if (childCount < 2) {
            super.addView(child)
        } else {
            throw IllegalStateException("Больше 2")
        }
    }
    fun handleChildError(index: Int, exception: Exception){
        Log.e("CustomContainer", "${exception.message}")
        getChildAt(index).visibility = GONE
    }
}