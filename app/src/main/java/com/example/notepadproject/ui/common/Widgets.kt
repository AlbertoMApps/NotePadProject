package com.example.notepadproject.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TestNiceButton() {
    NiceButton(title = "Test Button") { }
}

@Composable
fun NiceButton(
    title: String,
    textColor: Color = primaryTextColor,
    backgroundColor: Color = primaryColor,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(smallSpace)
            .height(normalTouchableHeight),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = textColor,
            containerColor = backgroundColor
        )
    ) {
        Text(title)
    }
}