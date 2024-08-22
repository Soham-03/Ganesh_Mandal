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
import com.gdsc.ganeshmandal.EditAuditActivity
import com.gdsc.ganeshmandal.Global
import com.gdsc.ganeshmandal.ListOfAuditsForSelectedMandal
import com.gdsc.ganeshmandal.MandalFinalAudit

@Composable
fun MandalIndividualAuditSingleRow(audit: MandalFinalAudit){
    val context = LocalContext.current
    Card(
        border = BorderStroke(2.dp, color = if(isSystemInDarkTheme()){Purple80}else{Purple40}),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                Global.selectedMandaAudit = audit
                if(Global.adminId.toString() == audit.auditId.toString()) {
                    val intent = Intent(context, EditAuditActivity::class.java)
                    context.startActivity(intent)
                }
                else{
                    println("Fuck off")
                }
            }
    ){
        Column(
            modifier = Modifier
                .padding(12.dp)
        ){
                Text(
                    text = Global.selectedMandal!!.nameOfMandal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = Global.selectedMandal!!.addOfMandal,
                    fontSize = 18.sp
                )
            }
            AnimatedVisibility(visible = true) {
                Column(
                    modifier = Modifier
                        .padding(12.dp)
                ){
                    Text(
                        text = "Total Score: "+audit.totalScoreText,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Id of Auditor: "+audit.auditId,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }