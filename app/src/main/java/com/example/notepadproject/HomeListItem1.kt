package com.example.notepadproject

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.notepadproject.ui.common.deleteIconButtonWidth
import com.example.notepadproject.ui.common.dividerNormalThickness
import com.example.notepadproject.ui.common.noRadius
import com.example.notepadproject.ui.common.noSpace
import com.example.notepadproject.ui.common.normalRadius
import com.example.notepadproject.ui.common.normalSpace
import com.example.notepadproject.ui.common.primaryColor
import com.example.notepadproject.ui.common.rowHeight

enum class RowType {
    TOP, MIDDLE, BOTTOM, SINGLE
}

@Composable
fun HomeListItem(
    title: String,
    subTitle: String,
    type: RowType,
    editMode: Boolean = false,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val shape = when (type) {
        RowType.TOP -> {
            RoundedCornerShape(normalRadius, normalRadius)
        }

        RowType.BOTTOM -> {
            RoundedCornerShape(
                noRadius,
                noRadius,
                normalRadius,
                normalRadius
            )
        }

        RowType.SINGLE -> {
            RoundedCornerShape(
                normalRadius,
                normalRadius,
                normalRadius,
                normalRadius
            )
        }

        else -> {
            RoundedCornerShape(noRadius)
        }
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(normalSpace, noSpace, normalSpace, noSpace)
                .fillMaxWidth()
                .height(rowHeight)
                .clip(shape)
                .background(color = primaryColor)
        ) {
            var deleteMode by remember { mutableStateOf(false) }
            if (editMode && !deleteMode) {
                Button(
                    onClick = {
                        deleteMode = !deleteMode
                    }, modifier = Modifier
                        .width(deleteIconButtonWidth)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(noRadius),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Red,
                        containerColor = primaryColor
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.DeleteForever,
                        contentDescription = "delete",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }

        }
        if (type != RowType.BOTTOM && type != RowType.SINGLE) {
            Divider(
                modifier = Modifier
                    .padding(normalSpace, noSpace, normalSpace, noSpace),
                color = Color.White,
                thickness = dividerNormalThickness
            )
        }
    }

}