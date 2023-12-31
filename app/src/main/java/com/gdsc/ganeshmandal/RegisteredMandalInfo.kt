package com.gdsc.ganeshmandal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.gdsc.ganeshmandal.ui.theme.RegisteredMandalInfoScreen

class RegisteredMandalInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val auditStatus = intent.getBooleanExtra("firstAuditStatus", false)
                    val auditStatus2 = intent.getBooleanExtra("secondAuditStatus", false)
                    RegisteredMandalInfoScreen(mandal = Global.selectedMandal!!, firstAuditStatus = auditStatus, auditStatus2)
                }
            }
        }
    }
}
