package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator
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
    var firstChildVisible by remember { mutableStateOf(false) }
    var secondChildVisible by remember { mutableStateOf(false) }
    var firstChildMoved by remember { mutableStateOf(false) }
    var secondChildMoved by remember { mutableStateOf(false) }

    // Блок активации анимации при первом запуске
    LaunchedEffect(Unit) {
        firstChildVisible = true
        firstChildMoved = true
        delay(2000)
        secondChildVisible = true
        secondChildMoved = true
    }
    val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(
        LocalContext.current
    )
    val bounds = windowMetrics.bounds
    var result = 0
    val resourceId = LocalContext.current.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = LocalContext.current.resources.getDimensionPixelSize(resourceId)
    }
    val density = LocalContext.current.resources.displayMetrics.density
    val childWayY = (bounds.height() / 2 - result - result/2)/density
    val offsetFirstChildY by animateDpAsState(
        targetValue = if (firstChildMoved) (-childWayY).dp else 0.dp,
        animationSpec = tween(durationMillis = 5000)
    )
    val offsetSecondChildY by animateDpAsState(
        targetValue = if (secondChildMoved) (childWayY).dp else 0.dp,
        animationSpec = tween(durationMillis = 5000)
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = firstChildVisible,
                enter = fadeIn(
                    animationSpec = tween(2000)
                ),
                modifier = Modifier.offset(y = offsetFirstChildY)
            ) {
                firstChild()
            }
        }

        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = secondChildVisible,
                enter = fadeIn(
                    animationSpec = tween(2000)
                ),
                modifier = Modifier.offset(y = offsetSecondChildY)
            ) {
                secondChild()
            }
        }

    }
}