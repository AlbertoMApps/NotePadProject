package com.example.notepadproject.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.notepadproject.data.model.Note

@Composable
fun HomeListLazy(
    deleteMode: Boolean,
    itemsSource: List<Note>,
    clickItemHandler: (Note) -> Unit,
    deleteItemHandler: (Note) -> Unit
) {
    LazyColumn {
        when (itemsSource.size) {
            1 -> itemsSource.first().let {
                item {
                    HomeListItem(
                        it.name, it.message, it.dateCreatedAt, RowType.SINGLE, deleteMode,
                        { clickItemHandler(it) },
                        { deleteItemHandler(it) }
                    )
                }
            }

            else -> {
                itemsIndexed(itemsSource) { index, item ->
                    val rowType = when (index) {
                        0 -> RowType.TOP
                        itemsSource.lastIndex -> RowType.BOTTOM
                        else -> RowType.MIDDLE
                    }
                    HomeListItem(
                        item.name, item.message, item.dateCreatedAt, rowType, deleteMode,
                        { clickItemHandler(item) },
                        { deleteItemHandler(item) }
                    )
                }
            }
        }
    }
}