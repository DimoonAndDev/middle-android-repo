package com.example.androidpracticumcustomview

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidpracticumcustomview.ui.theme.CustomContainer

class XmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        startXmlPracticum()
    }

    companion object {
        const val DELAY_SEC_VIEW_APP = 2000L
        const val DUR_VIEW_MOVE_ANIMATION = 5000L
        const val DUR_VIEW_ALPHA = 2000L
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)

        val firstView = TextView(this).apply {
            text = "first_view"
            setTextColor(getColor(R.color.black))
            gravity = Gravity.CENTER
            alpha = 0f
        }

        val secondView = TextView(this).apply {
            text = "second_view"
            setTextColor(getColor(R.color.black))
            gravity = Gravity.CENTER
            alpha = 0f
        }
        customContainer.addView(firstView)
        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, DELAY_SEC_VIEW_APP)
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        val viewWayY = resources.displayMetrics.heightPixels / 2f - result
        firstView.animate().translationY(-viewWayY).setDuration(DUR_VIEW_MOVE_ANIMATION)
            .start()
        firstView.animate().alpha(1f).setDuration(DUR_VIEW_ALPHA).start()
        secondView.animate().translationY(viewWayY).setDuration(DUR_VIEW_MOVE_ANIMATION)
            .setStartDelay(
                DELAY_SEC_VIEW_APP
            ).start()
        secondView.animate().alpha(1f).setDuration(DUR_VIEW_ALPHA).start()
    }
}