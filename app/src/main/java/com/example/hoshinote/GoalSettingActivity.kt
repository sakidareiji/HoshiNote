package com.example.hoshinote

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.hoshinote.ui.components.CustomHeader
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hoshinote.ui.theme.HoshiNoteTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults


class GoalSettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoshiNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ){
                    GoalInputScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalInputScreen() {
    val context = LocalContext.current
    
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "ユーザー") ?: "ユーザー"

    var goalTitle by remember { mutableStateOf("")}
    var goalDescription by remember { mutableStateOf("")}
    var expanded by remember { mutableStateOf(false)}
    val periods = stringArrayResource(id = R.array.goal_periods)
    var selectedPeriod by remember { mutableStateOf(periods[0]) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader(
            onSettingsClick = {
                val intent = Intent(context, SettingActivity::class.java)
                context.startActivity(intent)
            }
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFA0B0E21))
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
        Text(
            text = "こんにちは、${username}さん！",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFB0BEC5),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "目標設定",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB0BEC5),
            modifier = Modifier.padding(bottom = 24.dp)

        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            OutlinedTextField(
                value = goalTitle,
                onValueChange = {goalTitle = it},
                label = {Text("新しい目標のタイトル")},
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
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
            // 目標詳細入力フィールド
            OutlinedTextField(
                value = goalDescription,
                onValueChange = { goalDescription = it },
                label = { Text("目標の詳細")},
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(bottom = 12.dp), // 高さを指定
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

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {expanded = !expanded},
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            ) {
                OutlinedTextField(
                    value =  selectedPeriod,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("期間を選択", fontSize = 10.sp)},
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
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
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    periods.forEach { period ->
                        DropdownMenuItem(
                            text = { Text(period) },
                            onClick = {
                                selectedPeriod = period
                                expanded = false
                            }
                        )
                    }
                }
            }
            // 目標追加ボタン
            Button(
                onClick = {
                    if (goalTitle.isBlank()) {
                        Toast.makeText(context, "目標タイトルを入力してください", Toast.LENGTH_SHORT).show()
                    } else if (goalDescription.isBlank()) {
                        Toast.makeText(context, "目標の詳細を入力してください", Toast.LENGTH_SHORT).show()
                    } else {
                        val message = "目標:\nタイトル: $goalTitle\n詳細: $goalDescription\n期間: $selectedPeriod"
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

                        // 入力フィールドをクリア
                        goalTitle = ""
                        goalDescription = ""
                        selectedPeriod = periods[0] // 最初の期間に戻す
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "目標を追加", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        }
    }
    }

@Preview(showBackground = true)
@Composable
fun GoalInputScreenPreview() {
    HoshiNoteTheme {
        GoalInputScreen()
    }
}