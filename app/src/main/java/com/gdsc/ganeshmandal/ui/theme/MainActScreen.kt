package com.gdsc.ganeshmandal.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.AdminLoginPage
import com.gdsc.ganeshmandal.AllRegisteredMandalsList
import com.gdsc.ganeshmandal.RegisterMandalActivity

@Composable
fun MainActScreen(){
    val context = LocalContext.current
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Button(onClick = {
            val intent = Intent(context, RegisterMandalActivity::class.java)
            context.startActivity(intent)
        }, ) {
            Text(text = "Register\nNew Student", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }

        Button(onClick = {
            val intent = Intent(context, AllRegisteredMandalsList::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Admin\nLogin", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        }

    }

}

@Preview
@Composable
fun MainActScreenPreview(){
    MainActScreen()
}