package com.example.hoshinote.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun DailyEffortScreen() {
    var effortText by remember { mutableStateOf("") }
    var feelingText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFA0B0E21))
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "今日の頑張り",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
            )

            Text(
                text = "今日頑張ったこと",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
            )

            OutlinedTextField(
                value = effortText,
                onValueChange = { effortText = it },
                label = { Text("頑張ったことを入力してください") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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
                ),
                maxLines = 4
            )

            Text(
                text = "今の気持ち",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
            )

            OutlinedTextField(
                value = feelingText,
                onValueChange = { feelingText = it },
                label = { Text("今の気持ちを入力してください") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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
                ),
                maxLines = 4
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB0BEC5),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "エネルギー供給！",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}