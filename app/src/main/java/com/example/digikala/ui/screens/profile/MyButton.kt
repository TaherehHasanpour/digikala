package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.DigikalaRed
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing

@Composable
fun MyButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.DigikalaRed
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = localSpacing.current.semiLarge,
                end = localSpacing.current.semiLarge,
                bottom = localSpacing.current.medium,
            ),
        shape = localShape.current.small
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.h6
        )
    }
}