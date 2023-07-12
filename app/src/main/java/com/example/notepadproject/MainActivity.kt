package com.example.notepadproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.ui.HomeListItem
import com.example.notepadproject.ui.HomeListLazy
import com.example.notepadproject.ui.RowType
import com.example.notepadproject.ui.common.NiceButton
import com.example.notepadproject.ui.common.normalPadding
import com.example.notepadproject.ui.theme.NotepadProjectTheme
import com.example.notepadproject.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitUI()
        }

    }
}

@Composable
private fun InitUI(viewModel: NoteViewModel = hiltViewModel()) {
    val newNote = Note(
        name = "Untitled note",
        message = "",
        dateCreatedAt = SimpleDateFormat("dd MMM yyyy").format(
            Date()
        )
    )
    viewModel.addNote(
        newNote
    )
    val noteViewModelState = viewModel.state.value
    NotepadProjectTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column {
                Row(Modifier.padding(normalPadding)) {
                    NiceButton(title = stringResource(R.string.add_note)) {
                        viewModel.addNote(
                            newNote
                        )
                    }
                }
                Row(Modifier.padding(normalPadding)) {
                    HomeListLazy(
                        deleteMode = true,
                        itemsSource = (noteViewModelState.list),
                        clickItemHandler = {}
                    ) { note ->
                        viewModel.deleteNote(note)
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

