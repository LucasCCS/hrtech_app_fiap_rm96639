package com.hrtech.fiap

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hrtech.fiap.model.AuthViewModel
import com.hrtech.fiap.screen.LoginScreen
import com.hrtech.fiap.screen.OpportunityScreen
import com.hrtech.fiap.screen.SignupScreen
import com.hrtech.fiap.ui.theme.HrTechTheme
import androidx.compose.runtime.LaunchedEffect
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HrTechTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    MaterialTheme {
        Surface(color = Color.White) {
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(navController)
                }
                composable("signup") {
                    SignupScreen(navController)
                }
                composable("opportunity") {
                    OpportunityScreen(navController)
                }
            }
        }
    }
}

