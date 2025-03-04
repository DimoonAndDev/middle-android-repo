package com.example.androidpracticumcustomview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androidpracticumcustomview.ui.theme.AndroidPracticumCustomViewTheme
import com.example.androidpracticumcustomview.ui.theme.CustomContainerCompose

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticumCustomViewTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CustomContainerCompose(
                        firstChild = { FirstChild() },
                        secondChild = { SecondChild() })
                }
            }
        }
    }
}

@Composable
fun FirstChild() {
    Text(
        text = "first_child"
    )
}

@Composable
fun SecondChild() {
    Text(
        text = "second_child",
        color = Color.Black
    )
}
