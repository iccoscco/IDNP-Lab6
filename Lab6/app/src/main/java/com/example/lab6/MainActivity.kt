package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircleAnimationDemo()
        }
    }
}

@Composable
fun CircleAnimationDemo() {
    // Escala del círculo
    val scale = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Canvas para dibujar el círculo
        Canvas(modifier = Modifier.size(200.dp)) {
            drawCircle(
                color = Color(0xFF3F51B5),
                radius = 50f * scale.value,
                center = androidx.compose.ui.geometry.Offset(size.width / 2, size.height / 2)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para activar la animación
        Button(onClick = {
            val target = if (scale.value == 1f) 2f else 1f
            scope.launch {
                scale.animateTo(
                    targetValue = target,
                    animationSpec = tween(durationMillis = 800)
                )
            }
        }) {
            Text("Animar círculo", fontWeight = FontWeight.Bold)
        }
    }
}
