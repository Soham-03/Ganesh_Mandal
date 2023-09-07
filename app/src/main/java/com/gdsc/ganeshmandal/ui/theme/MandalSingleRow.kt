package com.gdsc.ganeshmandal.ui.theme

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.ganeshmandal.Global
import com.gdsc.ganeshmandal.Mandal
import com.gdsc.ganeshmandal.RegisteredMandalInfo

@Composable
fun MandalSingleRow(mandal: Mandal){
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .border(border = BorderStroke(2.dp, color = Color.Red), RoundedCornerShape(10.dp))
            .padding(12.dp)
            .clickable {
                Global.selectedMandal = mandal
                val intent = Intent(context, RegisteredMandalInfo::class.java)
                context.startActivity(intent)
            }
    ){
        Text(
            text = mandal.nameOfMandal,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = mandal.addOfMandal,
            fontSize = 18.sp
        )
        Text(
            text = "First Audit Status: "+mandal.firstAuditStatus,
            fontSize = 18.sp
        )
        Text(
            text = "Second Audit Status: "+mandal.firstAuditStatus,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun MandalSingleRowPreview(){
//    MandalSingleRow(mandal = Man)
}