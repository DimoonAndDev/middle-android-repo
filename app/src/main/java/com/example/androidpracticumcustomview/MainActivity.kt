package com.example.androidpracticumcustomview

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatButton

/*
Задание:
Реализуйте необходимые компоненты.
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var composeButton = findViewById<AppCompatButton>(R.id.main_scr_comp_btn)
        var xmlButton = findViewById<AppCompatButton>(R.id.main_scr_xml_btn)
        composeButton.setOnClickListener {
            var intent = Intent(this, ComposeActivity::class.java)
            startActivity(intent)
        }
        xmlButton.setOnClickListener {
            var intent = Intent(this, XmlActivity::class.java)
            startActivity(intent)
        }
    }/*
        /*
        Раскомментируйте нужный вариант
         */
        startXmlPracticum() // «традиционный» android (XML)
//          setContent { // Jetpack Compose
//             MainScreen()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)
        setContentView(customContainer)

        val firstView = TextView(this).apply {
            // TODO
            // ...
        }

        val secondView = TextView(this).apply {
            // TODO
            // ...
        }

        // Добавление второго элемента через некоторое время
        Handler(Looper.getMainLooper()).postDelayed({
            customContainer.addView(secondView)
        }, 2000)
    }
*/}