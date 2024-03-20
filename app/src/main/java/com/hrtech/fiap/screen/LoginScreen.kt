package com.hrtech.fiap.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hrtech.fiap.model.AuthViewModel
import com.hrtech.fiap.model.AuthenticationResponse
import com.hrtech.fiap.model.Credentials
import com.hrtech.fiap.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    val authViewModel = viewModel<AuthViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            Text(text = "Olá, bem-vindo(a)!", style = TextStyle(fontSize = 24.sp))
        }

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuário") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val credentials = Credentials(username = username.text, password = password.text)

                doLogin(credentials, authViewModel, navController)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Acessar")
        }

        Button(
            onClick = { navController.navigate("signup") },
            colors  = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "Criar conta", color = Color.Black)
        }
    }
}

fun doLogin(credentials: Credentials, authViewModel: AuthViewModel, navController: NavHostController) {

    RetrofitFactory().getAuthService().login(credentials).enqueue(object : Callback<AuthenticationResponse> {
        override fun onResponse(call: Call<AuthenticationResponse>, response: Response<AuthenticationResponse>) {

            if (response.isSuccessful) {
                Log.d("AuthenticationAPI", "sucesso")

                authViewModel.setToken(response.body()!!.accessToken);

                Log.d("AuthenticationAPI", "TOKEN: ${authViewModel.token.value}")

                navController.navigate("opportunity")

            } else {
                Log.d("AuthenticationAPI", "Erro ")
            }
        }
        override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
            Log.d("AuthenticationAPIFailure", "Falha de conexão com a API: ${t.message}")
        }
    })
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    val navController = rememberNavController()

    LoginScreen(navController)
}