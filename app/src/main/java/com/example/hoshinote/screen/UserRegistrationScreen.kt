package com.example.hoshinote.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.hoshinote.R
import com.example.hoshinote.navigation.Routes

@Composable
fun UserRegistrationScreen(navController: NavHostController){
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var characterAlphaTarget by remember { mutableStateOf(0f) }
    val characterAlpha by animateFloatAsState(
        targetValue = characterAlphaTarget,
        animationSpec = tween(durationMillis = 1000),
        label = "characterAlphaAnimation"
    )

    LaunchedEffect(Unit) {
        characterAlphaTarget = 1f
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B0E21))
            .padding(20.dp, 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
            Image(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = stringResource(R.string.character_content_description),
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
                    .alpha(characterAlpha)
            )

            Text(
                text = "ユーザー情報登録",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB0BEC5),
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF0B0E21),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(20.dp, 5.dp)
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("ユーザーネーム") },
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
                Button(
                    onClick = {
                        if (username.isBlank()) {
                            Toast.makeText(
                                context,
                                "ユーザーネームを入力してください",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val sharedPref =
                                context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                            with(sharedPref.edit()) {
                                putString("username", username)
                                apply()
                            }
                            Toast.makeText(
                                context,
                                "ユーザー名が登録されました",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(Routes.GoalManagement)
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0BEC5)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "ユーザネームを登録", color = Color.Black)
                }
            }
        }
    }