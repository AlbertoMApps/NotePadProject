package com.example.notepadproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notepadproject.data.Note
import com.example.notepadproject.ui.common.NiceButton

@Preview
@Composable
fun ListTest() {
    var editMode = false
    val data = remember {
        mutableStateListOf<Note>(
            Note("My NotepadProject Preview", "", "06/2023"),
            Note("First title note", "First message note", "06/2023")
        )
    }
    val title = if (editMode) "Cancel" else "Edit"

    Column {
        Row(Modifier.padding(16.dp)) {
            NiceButton(title = title) {
                editMode = !editMode
            }
            Spacer(Modifier.weight(1f))
            NiceButton(title = "Add") {
                data.add(Note("Test note added: ${data.size + 1}", "", "06/2023"))
            }
        }
        Row(Modifier.padding(16.dp)) {
            HomeListLazy(editMode = editMode, itemsSource = data, clickItemHandler = {}) { index ->
                data.removeAt(index)
            }
        }
    }

}