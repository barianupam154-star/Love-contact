package com.loveconnect.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Pink = Color(0xFFE94F91)
private val Purple = Color(0xFF7650D8)
private val Background = Color(0xFFFFF5FA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LoveConnectApp() }
    }
}

@Composable
fun LoveConnectApp() {
    var tab by remember { mutableStateOf(0) }
    MaterialTheme(colorScheme = lightColorScheme(primary = Pink, secondary = Purple, background = Background)) {
        Scaffold(
            containerColor = Background,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    val items = listOf("Discover", "Likes", "Chat", "Calls", "Profile")
                    val icons = listOf(Icons.Default.Explore, Icons.Default.FavoriteBorder,
                        Icons.Default.ChatBubbleOutline, Icons.Default.Videocam, Icons.Default.Person)
                    items.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = tab == index, onClick = { tab = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            when (tab) {
                0 -> Discover(Modifier.padding(padding))
                1 -> Placeholder("Your Likes", "People who like your profile will appear here.", Modifier.padding(padding))
                2 -> Placeholder("Messages", "Your matched conversations will appear here.", Modifier.padding(padding))
                3 -> Placeholder("Private Video Calls", "Your one-to-one call history will appear here.", Modifier.padding(padding))
                else -> Placeholder("Your Profile", "Profile setup and account settings will appear here.", Modifier.padding(padding))
            }
        }
    }
}

@Composable
private fun Discover(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("LoveConnect", color = Pink, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold)
                Text("Meet. Match. Chat. Connect.", color = Color.Gray)
            }
            Icon(Icons.Default.Favorite, contentDescription = null, tint = Pink)
        }
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(28.dp))
                .background(Brush.linearGradient(listOf(Pink, Purple))).padding(22.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Your next connection\ncould be one swipe away.",
                    color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Discover people from around the world.", color = Color.White)
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                    Text("Create your profile", color = Purple)
                }
            }
        }
        Text("Discover people", fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Box(
            Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(26.dp))
                .background(Brush.linearGradient(listOf(Color(0xFFFFD7E8), Color(0xFFDCD0FF)))),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(Modifier.size(100.dp).clip(CircleShape).background(Color.White.copy(alpha = .65f)),
                    contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, tint = Purple, modifier = Modifier.size(58.dp))
                }
                Text("Member profiles will appear here", color = Purple, fontWeight = FontWeight.SemiBold)
                Text("Connect your account to get started.", color = Purple.copy(alpha = .8f))
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Close, null); Spacer(Modifier.width(6.dp)); Text("Pass")
            }
            Button(onClick = {}, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Favorite, null); Spacer(Modifier.width(6.dp)); Text("Like")
            }
        }
    }
}

@Composable
private fun Placeholder(title: String, message: String, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(message, color = Color.Gray)
        Text("This screen is part of the starter UI. Backend services are not connected yet.", color = Purple)
    }
}
