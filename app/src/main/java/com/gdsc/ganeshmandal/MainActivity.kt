package com.gdsc.ganeshmandal

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.gdsc.ganeshmandal.ui.theme.MainActScreen
import com.gdsc.ganeshmandal.ui.theme.RegistrationForm

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Global.apply {
                        listOfAdminIdAndPassword["soham"] = "admin@123"
                        listOfAdminIdAndPassword["bryce"] = "admin@123"
                        listOfAdminIdAndPassword["RUPESH-9820315245"] = "admin@123"
                        listOfAdminIdAndPassword["KINNARI-9820806856"] = "admin@123"
                        listOfAdminIdAndPassword["MILIND-9869069069"] = "admin@123"
                        listOfAdminIdAndPassword["JUZER-9324295800"] = "admin@123"
                        listOfAdminIdAndPassword["RAVI-7718892211"] = "admin@123"
                        listOfAdminIdAndPassword["CHINTU-9372036266"] = "admin@123"
                        listOfAdminIdAndPassword["SURESH-9820185022"] = "admin@123"
                        listOfAdminIdAndPassword["AJIT-9820904282"] = "admin@123"
                        listOfAdminIdAndPassword["SHRREYASH-9820246186"] = "admin@123"
                        listOfAdminIdAndPassword["SANTOSH-9821086081"] = "admin@123"
                        listOfAdminIdAndPassword["VIVEK-9820191252"] = "admin@123"
                        listOfAdminIdAndPassword["KRISHNAKUMAR-9920743019"] = "admin@123"
                        listOfAdminIdAndPassword["RAJESH-9833726056"] = "admin@123"
                        listOfAdminIdAndPassword["SURENDRA-9920228275"] = "admin@123"
                        listOfAdminIdAndPassword["ROHIT-9920932083"] = "admin@123"
                        listOfAdminIdAndPassword["PARAG-9004322200"] = "admin@123"
                    }
                    MainActScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaneshMandalTheme {
        MainActScreen()
    }
}