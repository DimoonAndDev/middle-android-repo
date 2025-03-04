package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    firstChild: @Composable () -> Unit,
    secondChild: @Composable () -> Unit
) {
    // Блок создания и инициализации переменных
    // ..
    var view1Visible by remember { mutableStateOf(false) }
    var view2Visible by remember { mutableStateOf(false) }

    // Блок активации анимации при первом запуске
    LaunchedEffect(Unit) {
        view1Visible = true
        delay(2000)
        view2Visible = true
    }

    // Основной контейнер
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = view1Visible,
                modifier = Modifier,
                enter = fadeIn(
                    animationSpec = tween(2000)
                ),
                exit = fadeOut(
                    animationSpec = tween(300)
                )
            ) {
                firstChild()            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = view2Visible,
                modifier = Modifier,
                enter = fadeIn(
                    animationSpec = tween(5000)
                ),
                exit = fadeOut(
                    animationSpec = tween(300)
                )
            ) {
                secondChild()            }
        }

    }
}