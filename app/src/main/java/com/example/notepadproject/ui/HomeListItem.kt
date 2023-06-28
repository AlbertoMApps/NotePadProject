package com.example.notepadproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.example.notepadproject.ui.common.deleteButtonWidth
import com.example.notepadproject.ui.common.deleteIconButtonWidth
import com.example.notepadproject.ui.common.dividerNormalThickness
import com.example.notepadproject.ui.common.noRadius
import com.example.notepadproject.ui.common.noSpace
import com.example.notepadproject.ui.common.normalRadius
import com.example.notepadproject.ui.common.normalSpace
import com.example.notepadproject.ui.common.primaryColor
import com.example.notepadproject.ui.common.rowHeight
import com.example.notepadproject.ui.common.secondaryColor
import com.example.notepadproject.ui.common.smallSpace

enum class RowType {
    TOP, MIDDLE, BOTTOM, SINGLE
}

@Composable
fun HomeListItem(
    title: String,
    message: String,
    createdDate: String,
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
                .padding(normalSpace, normalSpace, normalSpace, normalSpace)
                .fillMaxWidth()
                .height(rowHeight)
                .clip(shape)
                .background(color = primaryColor)
        ) {

            Column(Modifier.weight(1f)) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(normalSpace)
                ) {
                    Text(
                        title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(smallSpace))
                    Text(
                        message,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(smallSpace))
                    Text(
                        createdDate,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            var deleteMode = false
            when {
                editMode && !deleteMode -> {
                    Button(
                        onClick = {
                            deleteMode = !deleteMode
                        },
                        modifier = Modifier
                            .width(deleteIconButtonWidth)
                            .fillMaxHeight(),
                        shape = RoundedCornerShape(noRadius),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = primaryColor,
                            containerColor = secondaryColor
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "delete",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        )
                    }
                }

                !editMode && !deleteMode -> {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "forward",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(Modifier.width(normalRadius))
                }

                editMode && deleteMode -> {
                    Button(
                        onClick = {
                            onDelete()
                            deleteMode = false
                        },
                        modifier = Modifier
                            .width(deleteButtonWidth)
                            .fillMaxHeight(),
                        shape = RoundedCornerShape(noRadius),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        )
                    ) {
                        Text("Delete")
                    }
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