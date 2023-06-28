package com.example.notepadproject.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.notepadproject.data.Note

@Composable
fun HomeListLazy(
    editMode: Boolean,
    itemsSource: SnapshotStateList<Note>,
    clickItemHandler: (Int) -> Unit,
    deleteItemHandler: (Int) -> Unit
) {
    LazyColumn {
        when (itemsSource.size) {
            1 -> itemsSource.first().let {
                item {
                    HomeListItem(
                        it.name, it.message, it.dateCreatedAt, RowType.SINGLE, editMode,
                        { clickItemHandler(0) },
                        { deleteItemHandler(0) }
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
                        item.name, item.message, item.dateCreatedAt, rowType, editMode,
                        { clickItemHandler(index) },
                        { deleteItemHandler(index) }
                    )
                }
            }
        }
    }
}