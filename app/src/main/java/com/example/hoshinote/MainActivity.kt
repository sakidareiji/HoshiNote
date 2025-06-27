package com.example.hoshinote

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.statusBarsPadding
import com.example.hoshinote.ui.theme.HoshiNoteTheme
import androidx.activity.SystemBarStyle
import androidx.compose.ui.graphics.toArgb


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                scrim = Color(0xAAB0BEC5).toArgb()
                ),
            navigationBarStyle = SystemBarStyle.dark(
                scrim = Color(0xd00B0E21).toArgb()
            )
        )
        setContent {
            HoshiNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFA0B0E21),
                ){
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation(){
    val navController= rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "userRegistration"
    ){
        composable("userRegistration"){
            UserRegistrationScreen(navController)
        }
        composable("goalSetting"){
            GoalSettingScreen(navController)
        }
        composable("goalManagement"){
            GoalManagementScreen(navController)
        }
        composable("setting"){
            SettingScreen(navController)
        }
    }
}
@Composable
fun CustomHeader(
    onSettingsClick: () -> Unit = {},
    onBackClick: (() -> Unit)? = null,
    showBackButton: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .background(Color(0xAAB0BEC5))
            .padding(horizontal = 16.dp)

    ) {
        if (showBackButton && onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "戻る",
                    tint = Color.White
                )
            }
        }

        Text(
            text = "HoshiNote",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )

        if (!showBackButton) {
            IconButton(
                onClick = onSettingsClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "設定",
                    tint = Color.White
                )
            }
        }
    }
}

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
        modifier = Modifier.fillMaxSize()
    ){
        CustomHeader(
            onSettingsClick = {
                navController.navigate("setting")
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFA0B0E21))
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
                            navController.navigate("goalSetting")
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalSettingScreen(navController: NavHostController){
    val context = LocalContext.current

    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "ユーザー") ?: "ユーザー"

    var goalTitle by remember { mutableStateOf("")}
    var goalDescription by remember { mutableStateOf("")}
    var expanded by remember { mutableStateOf(false)}
    val periods = stringArrayResource(id = R.array.goal_periods)
    var selectedPeriod by remember { mutableStateOf(if (periods.isNotEmpty()) periods[0] else "") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader(
            onSettingsClick = {
                navController.navigate("setting")
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
                            val goalString = "$goalTitle|$goalDescription|$selectedPeriod"
                            val goalSharedPref = context.getSharedPreferences("goal_prefs", Context.MODE_PRIVATE)
                            val existingGoals = goalSharedPref.getStringSet("goals", mutableSetOf()) ?: mutableSetOf()
                            val updatedGoals = existingGoals.toMutableSet()
                            updatedGoals.add(goalString)
                            
                            with(goalSharedPref.edit()) {
                                putStringSet("goals", updatedGoals)
                                apply()
                            }
                            
                            Toast.makeText(context, "目標が追加されました！", Toast.LENGTH_SHORT).show()
                            
                            goalTitle = ""
                            goalDescription = ""
                            selectedPeriod = if (periods.isNotEmpty()) periods[0] else ""
                            
                            navController.navigate("goalManagement")
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

@Composable
fun GoalManagementScreen(navController: NavHostController) {
    val context = LocalContext.current

    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val username = sharedPref.getString("username", "ユーザー") ?: "ユーザー"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader(
            onSettingsClick = {
                navController.navigate("setting")
            }
        )

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
                    navController.navigate("goalSetting")
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

@Composable
fun SettingScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomHeader(
            showBackButton = true,
            onBackClick = { navController.popBackStack() }
        )
        
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

