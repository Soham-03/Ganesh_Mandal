package com.gdsc.ganeshmandal.ui.theme

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.AdminLoginIdPass
import com.gdsc.ganeshmandal.AdminLoginPage
import com.gdsc.ganeshmandal.AdminOptions
import com.gdsc.ganeshmandal.AllRegisteredMandalsList
import com.gdsc.ganeshmandal.R
import com.gdsc.ganeshmandal.RegisterMandalActivity

@Composable
fun MainActScreen() {
    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ganesh),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, RegisterMandalActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Register New Mandal",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            Button(onClick = {
                val intent = Intent(context, AdminLoginIdPass::class.java)
                context.startActivity(intent)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Admin Login",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Preview
@Composable
fun MainActScreenPreview() {
    MainActScreen()
}