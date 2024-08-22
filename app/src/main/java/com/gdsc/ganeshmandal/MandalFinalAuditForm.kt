package com.gdsc.ganeshmandal

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.google.firebase.storage.FirebaseStorage

class MandalFinalAuditForm : ComponentActivity() {
    private val uriImageState = mutableStateOf<Uri?>(null)
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                Global.currentCapturedImage.set(index = (Global.clickedOnButton-1), element = uri)
                Global.currentCapturedImage[19] = uri
                Toast.makeText(this@MandalFinalAuditForm, uri.toString(), Toast.LENGTH_SHORT).show()
            }
            else{
//                Global.currentCapturedImage.add(Global.clickedOnButton-1,null)
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage = FirebaseStorage.getInstance()
        setContent {
            GaneshMandalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinalAuditForm(launcher, storage, true)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    GaneshMandalTheme {
//        FinalAuditForm()
    }
}