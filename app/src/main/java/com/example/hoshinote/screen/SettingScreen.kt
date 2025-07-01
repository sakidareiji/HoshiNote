package com.example.hoshinote.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hoshinote.components.CustomHeader

@Composable
fun SettingScreen() {
    var username by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFA0B0E21))
                .padding(20.dp)
        ) {
            Text(
                text = "ユーザーネーム変更",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("ユーザーネーム") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFB0BEC5),
                    unfocusedBorderColor = Color(0xFFB0BEC5),
                    focusedLabelColor = Color(0xFFB0BEC5),
                    unfocusedLabelColor = Color(0xFFB0BEC5),
                    focusedContainerColor = Color(0xFF0B0E21),
                    unfocusedContainerColor = Color(0xFF0B0E21),
                    focusedTextColor = Color(0xFFB0BEC5),
                    unfocusedTextColor = Color(0xFFB0BEC5)
                )
            )
        }
    }
}