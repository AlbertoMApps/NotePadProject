package com.example.notepadproject

import android.content.Context
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.notepadproject.data.Note
import com.example.notepadproject.ui.HomeListItem
import com.example.notepadproject.ui.HomeListLazy
import com.example.notepadproject.ui.RowType
import com.example.notepadproject.ui.common.NiceButton
import com.example.notepadproject.ui.common.normalPadding
import com.example.notepadproject.ui.theme.NotepadProjectTheme
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            initUI(baseContext)
        }
    }
}

@Composable
private fun initUI(context: Context) {
    NotepadProjectTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            val data = remember {//This will need to come from a view model in the future.
                mutableStateListOf<Note>(
                    Note("My NotepadProject Preview", "", "06/2023"),
                    Note("First title note", "First message note", "06/2023")
                )
            }//This will get replaced by Note's room DB table

            Column {
                Row(Modifier.padding(normalPadding)) {
                    NiceButton(title = stringResource(R.string.add_note)) {
                        data.add(
                            Note(
                                name = "Untitled note",
                                message = "",
                                dateCreatedAt = SimpleDateFormat("dd MMM yyyy").format(
                                    Date()
                                )
                            )
                        )
                    }
                }
                Row(Modifier.padding(normalPadding)) {
                    HomeListLazy(
                        deleteMode = true,
                        itemsSource = data,
                        clickItemHandler = {},
                        deleteItemHandler = { index ->
                            data.removeAt(index)
                        })
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

