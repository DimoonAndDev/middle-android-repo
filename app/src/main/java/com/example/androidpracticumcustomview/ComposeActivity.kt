package com.example.androidpracticumcustomview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
        setContent {
            AndroidPracticumCustomViewTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CustomContainerCompose(
                        firstChild = {
                            Text(
                                text = "first_child",
                                color = Color.Black
                            )
                        },
                        secondChild = {
                            Text(
                                text = "second_child",
                                color = Color.Black
                            )
                        })
                }
            }
        }
    }
}
