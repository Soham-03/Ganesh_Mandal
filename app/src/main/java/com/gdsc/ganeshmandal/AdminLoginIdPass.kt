package com.gdsc.ganeshmandal

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme

class AdminLoginIdPass : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var adminId by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    var adminPass by remember {
                        mutableStateOf(TextFieldValue(""))
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    )
                    {
                        OutlinedTextField(
                            value = adminId,
                            onValueChange = {
                                adminId = it
                            },
                            enabled = true,
                            placeholder = {
                                Text(text = "Enter Id")
                            },
                            label = {
                                Text(text = "Enter Id")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = adminPass,
                            onValueChange = {
                                adminPass = it
                            },
                            enabled = true,
                            placeholder = {
                                Text(text = "Enter Password")
                            },
                            label = {
                                Text(text = "Enter Password")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick ={
                            if(
                                !TextUtils.isEmpty(adminId.text) &&
                                !TextUtils.isEmpty(adminPass.text)
                            ){
                                if(Global.listOfAdminIdAndPassword[adminId.text] == adminPass.text){
                                    Global.adminId = adminId.text
                                    Toast.makeText(this@AdminLoginIdPass, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this@AdminLoginIdPass, AdminOptions::class.java)
                                    startActivity(intent)
                                }
                                else{
                                    Toast.makeText(this@AdminLoginIdPass, "InCorrect Password", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else{
                                Toast.makeText(this@AdminLoginIdPass, "Fill all Fields", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text(
                                text = "Log In",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    GaneshMandalTheme {

    }
}