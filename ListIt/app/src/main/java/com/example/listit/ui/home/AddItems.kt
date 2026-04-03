package com.example.listit.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun AddItems(viewModel: HomeViewModel){
    val context = LocalContext.current
    val color = getTheCurrentTheme(context)
    val currentColor = ThemeColor(color)
    var title by remember { mutableStateOf("") }
    var task by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf(false) }
    Scaffold(){innerPadding->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
            Spacer(Modifier.height(20.dp))
            TextField(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .fillMaxWidth(),
                value = task,
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
            if (title.isEmpty() and errorText) {
                Text("Please enter the title", color = Color.Red)
            }
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (title.isEmpty()) {
                            errorText = true
                        } else {
                            SaveTask(
                                title = title,
                                task = task,
                                viewModel = viewModel,
                                context = context
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = currentColor.contentColor,
                        containerColor = currentColor.containerColor
                    )
                ) {
                    Text("Save")
                }
                Button(
                    onClick = {},
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

