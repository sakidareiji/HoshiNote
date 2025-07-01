package com.example.hoshinote.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hoshinote.components.CustomHeader
import com.example.hoshinote.R
import com.example.hoshinote.navigation.Routes

@Composable
fun GoalManagementScreen(navController: NavHostController) {
    val context = LocalContext.current

    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "ユーザー") ?: "ユーザー"

    var goalTitle by remember { mutableStateOf("") }
    var goalDescription by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val periods = stringArrayResource(id = R.array.goal_periods)
    var selectedPeriod by remember { mutableStateOf(periods[0]) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFA0B0E21))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val goalSharedPref = context.getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
            val savedGoals = goalSharedPref.getStringSet("goals", emptySet()) ?: emptySet()

            if (savedGoals.isEmpty()) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "まだ目標が設定されていません",
                        fontSize = 16.sp,
                        color = Color(0xFFB0BEC5),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                Text(
                    text = "設定した目標",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB0BEC5),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(items = savedGoals.toList()) { goalString ->
                        val goalData = goalString.split("|")
                        if (goalData.size == 3) {
                            val title = goalData[0]
                            val description = goalData[1]
                            val period = goalData[2]

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFF1A1A2E)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = title,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFB0BEC5),
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = description,
                                        fontSize = 14.sp,
                                        color = Color(0xFFB0BEC5),
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                    Text(
                                        text = "期間: $period",
                                        fontSize = 12.sp,
                                        color = Color(0xFF90A4AE)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {
                    navController.navigate(Routes.GoalSetting)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "新しい目標を設定", color = Color.Black)
            }
        }
    }
}