package com.example.notepadproject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepadproject.R
import com.example.notepadproject.presentation.NoteViewModel
import com.example.notepadproject.ui.common.NiceButton
import com.example.notepadproject.ui.common.newNote
import com.example.notepadproject.ui.common.normalPadding
import com.example.notepadproject.ui.screens.HomeListItem
import com.example.notepadproject.ui.screens.HomeListLazy
import com.example.notepadproject.ui.screens.RowType
import com.example.notepadproject.ui.theme.NotepadProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotepadHomeScreen()

        }

    }
}

@Composable
private fun NotepadHomeScreen(viewModel: NoteViewModel = hiltViewModel()) {

    viewModel.addNote(
        newNote
    )

    val notes = viewModel.state.value
    val errorMessage = notes.errorMessage

    NotepadProjectTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Column {
                Row(Modifier.padding(normalPadding)) {
                    NiceButton(
                        title = stringResource(R.string.add_note),
                        onClick = {
                            viewModel.addNote(newNote)
                        }
                    )
                }
                Row(Modifier.padding(normalPadding)) {
                    HomeListLazy(
                        deleteMode = true,
                        itemsSource = (notes.list),
                        clickItemHandler = {}
                    ) { note ->
                        viewModel.deleteNote(note)
                    }
                }
                if (errorMessage.isNotEmpty()) {
                    Row(Modifier.padding(normalPadding)) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colors.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = normalPadding)
                        )
                    }
                }
            }
        }
    }
}

/****
 * Preview UI tests
 */

@Preview(showBackground = true)
@Composable
fun TestSingle() {
    HomeListItem("Daily Notes", "First message", "06/23", RowType.SINGLE, true, {}, {})
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

