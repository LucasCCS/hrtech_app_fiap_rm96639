package com.hrtech.fiap.component

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.hrtech.fiap.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    content: @Composable (PaddingValues) -> Unit = { },
    searchInput: @Composable (PaddingValues) -> Unit = { },
    navController: NavHostController
) {
    var isCenterButtonVisible by remember { mutableStateOf(true) }
    val centerButtonOffset by animateDpAsState(
        targetValue = if (isCenterButtonVisible) 0.dp else (-32).dp,
        animationSpec = spring()
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(android.graphics.Color.parseColor("#FFFFFF"))
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        // Avatar
                        Image(
                            painter = painterResource(id = R.drawable.avatar_placeholder),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .size(30.dp)
                        )
                        // Campo de busca
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .height(50.dp)
                                .background(Color(android.graphics.Color.parseColor("#EAEAEA"))),


                            ) {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                searchInput(PaddingValues(0.dp))
                            }
                        }

                        // Botão 2
                        IconButton(onClick = { /* Ação do botão 2 */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.message_square),
                                contentDescription = "Chat",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = (32).dp)
                    .zIndex(1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(100),
                    contentColor = androidx.compose.ui.graphics.Color.White,
                    containerColor = colorResource(R.color.primary),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                        .zIndex(1f)
                ) {
                    IconButton(
                        onClick = { /* Ação do botão */ },
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Adicionar")
                    }
                }
            }

            content(PaddingValues(0.dp))

        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(android.graphics.Color.parseColor("#F5F6FA")),
                contentColor = Color(android.graphics.Color.parseColor("#666666")),
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            IconButton(onClick = { /* Ação do botão */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "Feed",
                                    modifier = Modifier.size(32.dp))
                            }
                            IconButton(onClick = { /* Ação do botão */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.users),
                                    contentDescription = "Minha rede",
                                    modifier = Modifier.size(32.dp))
                            }
                        }
                        Row {
                            IconButton(onClick = { /* Ação do botão */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.bell),
                                    contentDescription = "Notificações",
                                    modifier = Modifier.size(32.dp))
                            }
                            IconButton(onClick = {
                                navController.navigate("opportunity")
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.briefcase),
                                    contentDescription = "Oportunidades",
                                    modifier = Modifier.size(32.dp))
                            }
                        }
                    }
                }
            )
        }
    )
}
