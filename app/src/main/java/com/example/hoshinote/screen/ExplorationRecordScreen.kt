package com.example.hoshinote.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExplorationRecordScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFA0B0E21))
                .padding(20.dp)
        ) {
            Text(
                text = "探索記録",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}