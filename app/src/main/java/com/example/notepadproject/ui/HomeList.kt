package com.example.notepadproject.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.notepadproject.data.Note


@Composable
fun HomeList(
    editMode: Boolean,
    items: SnapshotStateList<Note>,
    clickItemHandler: (Int) -> Unit,
    deleteItemHandler: (Int) -> Unit
) {
    Column {
        when (items.size) {
            1 -> items.first().let {
                HomeListItem(
                    it.name, it.dateCreatedAt, RowType.SINGLE, editMode,
                    { clickItemHandler(0) },
                    { deleteItemHandler(0) }
                )
            }//Special initial case for a single row as Intro.

            else -> {
                items.forEachIndexed { index, data ->
                    val rowType = when (index) {
                        0 -> RowType.TOP
                        items.lastIndex -> RowType.BOTTOM
                        else -> RowType.MIDDLE
                    }
                    HomeListItem(
                        data.name, data.dateCreatedAt, rowType, editMode,
                        { clickItemHandler(index) },
                        { deleteItemHandler(index) }
                    )
                }
            } // Rest of notes represented.
        }
    }
}