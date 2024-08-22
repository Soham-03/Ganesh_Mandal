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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.Global
import com.gdsc.ganeshmandal.R
import com.gdsc.ganeshmandal.model.Mandal
import com.gdsc.ganeshmandal.RegisteredMandalInfo

@Composable
fun MandalSingleRow(
    mandal: Mandal,
    show1stAuditStatus: Boolean,
    show2ndAuditStatus: Boolean,
    listOfAuditId: ArrayList<String>?,
    inRegistered: Boolean,
    isSecondAudit: Boolean = false
) {
    val context = LocalContext.current




    Card(

//        border = BorderStroke(2.dp, color = if(isSystemInDarkTheme()){Purple80}else{Purple40}),
        border = if (!inRegistered) {
            if(isSecondAudit){
                if (mandal.secondAuditStatus == "true") BorderStroke(
                    2.dp,
                    color = GreenColor
                ) else BorderStroke(2.dp, color = RedColor)
            }
            else{
                if (mandal.firstAuditStatus == "true") BorderStroke(
                    2.dp,
                    color = GreenColor
                ) else BorderStroke(2.dp, color = RedColor)
            }
        } else {
            BorderStroke(2.dp, Color.Black)
        },
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                Global.selectedMandal = mandal
                val intent = Intent(context, RegisteredMandalInfo::class.java)
                intent.putExtra("firstAuditStatus", show1stAuditStatus)
                intent.putExtra("secondAuditStatus", show2ndAuditStatus)
                context.startActivity(intent)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            if (mandal != null) {
                Text(
                    text = mandal.nameOfMandal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mandal.addOfMandal,
                    fontSize = 18.sp
                )
                AnimatedVisibility(visible = show1stAuditStatus) {
                    Text(
                        text = "First Audit Status: " + mandal.firstAuditStatus,
                        fontSize = 18.sp,
                        color = if (mandal.firstAuditStatus == "true") GreenColor else RedColor
                    )
                }
            }
            AnimatedVisibility(visible = show2ndAuditStatus) {
                if (mandal != null) {
                    Column {
                        Text(
                            text = "Second Audit Status: " + mandal.secondAuditStatus,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Total Score: " + mandal.totalScore,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MandalSingleRowPreview() {
//    MandalSingleRow(mandal = Man)
}