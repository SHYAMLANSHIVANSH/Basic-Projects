package com.example.listit.ui.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listit.utils.ThemeColor
import com.example.listit.utils.getTheCurrentTheme


@Composable
fun EditItems(viewModel: HomeViewModel, onBack: () -> Unit, id: Int){
    val context = LocalContext.current
    val color = getTheCurrentTheme(context)
    val currentColor = ThemeColor(color)
    var title by remember {mutableStateOf("")}
    var task: String? by remember {mutableStateOf("")}

    val tasks = viewModel.task.collectAsState().value

    LaunchedEffect(true) {
        viewModel.loadTasks(context)
    }

    val taskItem = tasks.find { it.Id == id }

    LaunchedEffect(true) {
        if(taskItem != null){
            task = taskItem.Task
            title = taskItem.Title
        }
    }



    var errorText by remember { mutableStateOf(false) }
    Scaffold(){innerPadding->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item(){
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .fillMaxWidth(),
                    value = title,
                    onValueChange = { title = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = currentColor.contentColor,
                        focusedTextColor = currentColor.contentColor,
                        cursorColor = currentColor.contentColor,
                        focusedContainerColor = currentColor.containerColor,
                        unfocusedContainerColor = currentColor.containerColor,
                        unfocusedLabelColor = currentColor.contentColor,
                        focusedLabelColor = currentColor.contentColor
                    ),
                    label = { Text("Enter Title") }
                )
            }
            item(){ Spacer(Modifier.height(20.dp)) }
            item(){
                TextField(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .fillMaxWidth(),
                    value = task.toString(),
                    onValueChange = { task = it },
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = currentColor.contentColor,
                        focusedTextColor = currentColor.contentColor,
                        cursorColor = currentColor.contentColor,
                        focusedContainerColor = currentColor.containerColor,
                        unfocusedContainerColor = currentColor.containerColor,
                        unfocusedLabelColor = currentColor.contentColor,
                        focusedLabelColor = currentColor.contentColor
                    ),
                    label = { Text("Enter Task") }
                )
            }
            if (title.isEmpty() and errorText) {
                item(){ Text("Please enter the title", color = Color.Red) }
            }
            item(){ Spacer(Modifier.height(20.dp)) }
            item(){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            if (title.isEmpty()) {
                                errorText = true
                            } else {
                                UpdateTask(
                                    title = title,
                                    task = task,
                                    Id = id,
                                    viewModel = viewModel,
                                    context = context
                                )
                                onBack()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = currentColor.contentColor,
                            containerColor = currentColor.containerColor
                        )
                    ) {
                        Text("Update")
                    }
                    Button(
                        onClick = {
                            onBack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = currentColor.contentColor,
                            containerColor = currentColor.containerColor
                        )
                    ) {
                        Text("Reject")
                    }
                }
            }
        }
    }
}

