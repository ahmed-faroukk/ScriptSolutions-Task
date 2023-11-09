package com.cyberdunkers.script_solution_task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.cyberdunkers.script_solution_task.R
import com.cyberdunkers.script_solution_task.data.repository.NetworkConnectivityObserverImp
import com.cyberdunkers.script_solution_task.domin.repository.ConnectivityObserverRepo
import com.cyberdunkers.script_solution_task.presentation.AppCompnents.InfoDialog
import com.cyberdunkers.script_solution_task.presentation.home.MainScreen
import com.cyberdunkers.script_solution_task.presentation.ui.theme.ScriptSolutionTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserverRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserverImp(applicationContext)

        setContent {
            val infoDialog = remember { mutableStateOf(true) }
            val firstLost = remember { mutableStateOf(true) }
            val status = connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserverRepo.Status.Available
            )

            ScriptSolutionTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (status.value != ConnectivityObserverRepo.Status.Available && infoDialog.value) {
                        InfoDialog(
                            title = "Whoops!",
                            desc = "No Internet Connection found.\n" +
                                    "Check your connection or try again.",
                            onDismiss = {
                                infoDialog.value = false
                                firstLost.value = false
                            }

                        )


                    }

                    if (status.value == ConnectivityObserverRepo.Status.Available && !firstLost.value) {
                        InfoDialog(
                            title = "Back Online",
                            desc = "your connection is back again",
                            imgId = R.drawable.outline_wifi_24,
                            onDismiss = {
                                infoDialog.value = true
                                firstLost.value = true
                            }
                        )

                    }
                    LaunchedEffect(key1 = Unit) {
                        if (!infoDialog.value){
                            lifecycleScope.launch {
                                delay(5000)
                            }.invokeOnCompletion {
                                infoDialog.value = true
                            }
                        }
                    }
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScriptSolutionTaskTheme {
        Greeting("Android")
    }
}