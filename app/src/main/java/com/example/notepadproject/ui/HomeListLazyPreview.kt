package com.example.notepadproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notepadproject.R
import com.example.notepadproject.data.model.Note
import com.example.notepadproject.ui.common.NiceButton
import com.example.notepadproject.ui.common.normalPadding
import java.text.SimpleDateFormat
import java.util.Date

@Preview
@Composable
fun ListTest() {
    val deleteMode = false
    val data = remember {
        arrayListOf<Note>(
            Note("My NotepadProject Preview", "", "06/2023"),
            Note("First title note", "First message note", "06/2023")
        )
    }

    Column {
        Row(Modifier.padding(normalPadding)) {
            NiceButton(title = stringResource(id = R.string.add_note)) {
                data.add(
                    Note(
                        "Test note added: ${data.size + 1}",
                        "",
                        SimpleDateFormat("dd MMM yyyy").format(
                            Date()
                        )
                    )
                )
            }
        }
        Row(Modifier.padding(normalPadding)) {
            HomeListLazy(
                deleteMode = deleteMode,
                itemsSource = data,
                clickItemHandler = {},
                deleteItemHandler = { data.remove(it) })
        }
    }

}