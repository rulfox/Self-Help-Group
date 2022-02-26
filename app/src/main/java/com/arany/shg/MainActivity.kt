package com.arany.shg

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arany.shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.ui.LoginPage
import com.arany.shg.ui.LoginViewModel
import com.arany.shg.ui.theme.SelfHelpGroupTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var selfHelpGroupRepository: SelfHelpGroupRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfHelpGroupTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
        testingDatabase()
    }

    private fun testingDatabase(){
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("Aswin", "### Comment Start")
            /*try {
                for(i in 1..100)
                selfHelpGroupRepository.createSelfHelpGroup(SelfHelpGroup(name = "Aswin's SHG -> $i", address = "Arany -> $i"))
            }catch (e: Exception){
                Log.e("Aswin", "Exception -> ${e.message}")
            }*/
            try {
                Log.e("Aswin", Gson().toJson(selfHelpGroupRepository.getAllSelfHelpGroups().first()))
            } catch (e: Exception){
                Log.e("Aswin", "Exception -> ${e.message}")
            }
            Log.e("Aswin", "### Comment End")
        }
    }
}

@Composable
fun Greeting(name: String) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),

        value = name,
        onValueChange = { },
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SelfHelpGroupTheme {
        Greeting("Android")
    }
}