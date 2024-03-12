package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.DarkCyan
import com.example.digikala.ui.theme.cursorColor
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.searchBarBg


@Composable
fun MyEditText(
    value: String,
    onValueChange: (it: String) -> Unit,
    placeholder:String
) {
    TextField(
        value = value, onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                start = localSpacing.current.semiLarge,
                end = localSpacing.current.semiLarge,
                bottom = localSpacing.current.semiLarge,
                top = localSpacing.current.medium,
            ),
        shape= localShape.current.small,
        colors= TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colors.DarkCyan,
            unfocusedIndicatorColor = MaterialTheme.colors.searchBarBg,
            cursorColor = MaterialTheme.colors.cursorColor
        ),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = placeholder,
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )
}