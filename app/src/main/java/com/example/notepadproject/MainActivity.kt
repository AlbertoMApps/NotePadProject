package com.example.notepadproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.notepadproject.ui.HomeListItem
import com.example.notepadproject.ui.RowType
import com.example.notepadproject.ui.theme.NotepadProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotepadProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeListItem(
                        "Daily notes",
                        "First message",
                        "06/23",
                        RowType.SINGLE,
                        false,
                        {},
                        {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestSingle() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.SINGLE, false, {}, {})
}

@Preview
@Composable
fun TestTop() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.TOP, false, {}, {})
}

@Preview
@Composable
fun TestMiddle() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.MIDDLE, false, {}, {})
}

@Preview
@Composable
fun TestBottom() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.BOTTOM, false, {}, {})
}

@Preview
@Composable
fun TestBottomEdit() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.BOTTOM, true, {}, {})
}

