package com.hrtech.fiap.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OpportunityScreen(navController: NavHostController) {
    var listJobs by remember { mutableStateOf(listOf<JobOpportunity>()) }
    var searchText by remember { mutableStateOf("") }


    AppScaffold(
        navController = navController,
        searchInput = {
            OutlinedTextField(
                value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                },
                placeholder = { Text("Pesquisar") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                )
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(top = 80.dp, start = 0.dp, end = 0.dp, bottom = 0.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LaunchedEffect(searchText) {
                        Log.d("JobOpportunity", "LaunchedEffect")
                        RetrofitFactory().getJobOpportunityService().getJobOpportunity(searchText).enqueue(object : Callback<List<JobOpportunity>> {
                            override fun onResponse(call: Call<List<JobOpportunity>>, response: Response<List<JobOpportunity>>) {

                                if (response.isSuccessful) {
                                    listJobs = response.body() ?: emptyList()
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
                        Text("${listJobs.size} vagas encontradas")

                    }
                    LazyColumn() {
                        items(listJobs) {
                            JobOpportunityComponent(it, onClick = {})
                        }
                    }
                }

            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewOpportunityScreen() {
    val navController = rememberNavController()
    OpportunityScreen(navController)
}
