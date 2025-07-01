package com.example.hoshinote.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xd00B0E21))
            .padding(horizontal = 16.dp)

    ) {
        Text(
            text = "HoshiNote",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}