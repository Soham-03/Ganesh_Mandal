package com.gdsc.ganeshmandal.ui.theme

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.Global
import com.gdsc.ganeshmandal.ListOfAuditsForSelectedMandal
import com.gdsc.ganeshmandal.model.Mandal

@Composable
fun MandalAuditSingleRow(mandal: Mandal){
    val context = LocalContext.current
    Card(
        border = BorderStroke(2.dp, color = if(isSystemInDarkTheme()){Purple80}else{Purple40}),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                Global.selectedMandal = mandal
                println("Mandal Id:"+Global.selectedMandal!!.mandalId)
                val intent = Intent(context, ListOfAuditsForSelectedMandal::class.java)
                context.startActivity(intent)
            }
    ){
        Column(
            modifier = Modifier
                .padding(12.dp)
        ){
            if(mandal != null){
                Text(
                    text = mandal.nameOfMandal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mandal.addOfMandal,
                    fontSize = 18.sp
                )
            }
            AnimatedVisibility(visible = true) {
                if(mandal!=null){
                    Column {
                        Text(
                            text = "Second Audit Status: "+mandal.secondAuditStatus,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Total Score: "+mandal.finalAuditScore,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}