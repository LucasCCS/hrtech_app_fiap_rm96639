package com.hrtech.fiap.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.hrtech.fiap.component.AppScaffold
import com.hrtech.fiap.component.JobOpportunityComponent
import com.hrtech.fiap.model.JobOpportunity
import com.hrtech.fiap.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OpportunityScreen(navController: NavHostController) {
    AppScaffold(
        navController = navController,
        content = {
            Box(
                modifier = Modifier.padding(top = 80.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LaunchedEffect(true) {
                        Log.d("JobOpportunity", "LaunchedEffect")
                        RetrofitFactory().getJobOpportunityService().getJobOpportunity().enqueue(object : Callback<List<JobOpportunity>> {
                            override fun onResponse(call: Call<List<JobOpportunity>>, response: Response<List<JobOpportunity>>) {

                                if (response.isSuccessful) {
                                    val responseBody = response.body()
                                    val responseBodyString = Gson().toJson(responseBody)
                                    Log.d("JobOpportunitySuccess", ": $responseBodyString")

                                    val posts: List<JobOpportunity>? = response.body()
                                    // Faça algo com a lista de posts
                                } else {
                                    Log.d("JobOpportunityErrorAPI", "Erro ao se comunicar com a API")
                                }
                            }

                            override fun onFailure(call: Call<List<JobOpportunity>>, t: Throwable) {
                                Log.d("JobOpportunityFailure", "Falha de conexão com a API: ${t.message}")
                            }
                        })
                    }


                    Row (
                        modifier = Modifier
                            .padding(bottom = 22.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text("10 vagas recomendadas para o seu perfil")

                    }

                    val item = JobOpportunity(1, "Desenvolvedor Backend JAVA","FIAP");
                    JobOpportunityComponent(item, onClick = {})
                    JobOpportunityComponent(item, onClick = {})
                }

            }
        })
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOpportunityScreen() {
    val navController = rememberNavController()
    OpportunityScreen(navController)
}
