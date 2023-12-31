package com.example.notepadproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.notepadproject.data.model.Note


@Composable
fun HomeList(
    deleteMode: Boolean,
    items: ArrayList<Note>,
    clickItemHandler: (Int) -> Unit,
    deleteItemHandler: (Int) -> Unit
) {
    Column {
        when (items.size) {
            1 -> items.first().let {
                HomeListItem(
                    it.name ?: "",
                    it.message ?: "",
                    it.dateCreatedAt ?: "",
                    RowType.SINGLE,
                    deleteMode,
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
                        data.name ?: "",
                        data.message ?: "",
                        data.dateCreatedAt ?: "",
                        rowType,
                        deleteMode,
                        { clickItemHandler(index) },
                        { deleteItemHandler(index) }
                    )
                }
            } // Rest of notes represented.
        }
    }
}