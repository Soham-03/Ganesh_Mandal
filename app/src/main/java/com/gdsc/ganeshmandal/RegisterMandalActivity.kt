package com.gdsc.ganeshmandal

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.gdsc.ganeshmandal.ui.theme.GaneshMandalTheme
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.github.drjacky.imagepicker.ImagePicker
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.storage.FirebaseStorage
import java.util.Calendar
import java.util.UUID
import org.checkerframework.checker.units.qual.s
import java.util.regex.Matcher


class RegisterMandalActivity : ComponentActivity() {

    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    private val uriImageState = mutableStateOf<Uri?>(null)
    private var latitude = mutableStateOf("")
    private var longitude = mutableStateOf("")
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                uriImageState.value = uri
                Toast.makeText(
                    this@RegisterMandalActivity, uriImageState.toString(), Toast.LENGTH_SHORT
                ).show()
            } else {
                latitude.value = ""
                longitude.value = ""
            }
        }

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage = FirebaseStorage.getInstance()
        setContent {
            GaneshMandalTheme {
                var isPermissionGranted by remember { mutableStateOf(false) }
                val context = LocalContext.current
                var permissionLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission()
                ) { isGranted ->
                    isPermissionGranted = isGranted
                }
                LaunchedEffect(key1 = true) {
                    if (!hasLocationPerms()) {
                        requestPerms(permissionLauncher)
//                        permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                    }
                }
                var progressState by remember {
                    mutableStateOf(false)
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val db = FirebaseFirestore.getInstance()
                        var nameOfMandal by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var addOfMandal by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var contactPerson by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var mobileNo by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var totalAreaCovered by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var totalAreaOpen by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var dateOfTechnicalEvaluation by remember {
                            mutableStateOf("")
                        }
                        // Electrical Safety
                        var powerConsumedByMandal by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var sizeOfCableInstalled by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var typeOfCable by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var yesOrNo = arrayOf("Yes", "No")
                        var mcb_mccbInstalled by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var mandalRepresentativeEmail by remember {
                            mutableStateOf(TextFieldValue(""))
                        }

                        var sourceOfBackupPowerSupply by remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        var earthingWireQuant by remember {
                            mutableStateOf(TextFieldValue(""))
                        }

//                        val backupPowerSupplyYesOrNo:String,
//                        val sourceOfBackupPowerSupply:String,


                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(12.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = "Technical Evaluation Form",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(value = nameOfMandal, onValueChange = {
                                nameOfMandal = it
                            }, placeholder = {
                                Text(text = "Name of Mandal")
                            }, label = {
                                Text(text = "Name of Mandal")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = addOfMandal, onValueChange = {
                                addOfMandal = it
                            }, placeholder = {
                                Text(text = "Address of Mandal")
                            }, label = {
                                Text(text = "Address of Mandal")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = contactPerson, onValueChange = {
                                contactPerson = it
                            }, placeholder = {
                                Text(text = "Contact Person")
                            }, label = {
                                Text(text = "Contact Person")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = mobileNo,
                                onValueChange = {
                                    if (it.text.length <= 10) mobileNo = it
                                },
                                placeholder = {
                                    Text(text = "Mobile No.")
                                },
                                label = {
                                    Text(text = "Mobile No.")
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(
                                value = mandalRepresentativeEmail,
                                onValueChange = {
                                    mandalRepresentativeEmail = it
                                },
                                placeholder = {
                                    Text(text = "Mandal Representative Email")
                                },
                                label = {
                                    Text(text = "Mandal Representative Email")
                                },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = totalAreaCovered,
                                    onValueChange = {
                                        totalAreaCovered = it
                                    },
                                    placeholder = {
                                        Text(text = "Total Area(Covered)")
                                    },
                                    label = {
                                        Text(text = "Total Area(Covered)")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                )
                                Text("  Sq. ft.")
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = totalAreaOpen,
                                    onValueChange = {
                                        totalAreaOpen = it
                                    },
                                    placeholder = {
                                        Text(text = "Total Area(Open)")
                                    },
                                    label = {
                                        Text(text = "Total Area(Open)")
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                                )
                                Text("  Sq. ft.")
                            }
                            val context = LocalContext.current
                            val calendar = Calendar.getInstance()
                            val year = calendar[Calendar.YEAR]
                            val month = calendar[Calendar.MONTH]
                            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
                            val datePicker = DatePickerDialog(
                                context,
                                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                                    dateOfTechnicalEvaluation =
                                        "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                                },
                                year,
                                month,
                                dayOfMonth
                            )
                            datePicker.datePicker.minDate = calendar.timeInMillis

                            Row() {
                                Column {
                                    Text(text = "Date of Technical evaluation:")
                                    Text(text = dateOfTechnicalEvaluation)
                                }
                                Button(onClick = {
                                    datePicker.show()
                                }) {
                                    Text(text = "Select date", fontSize = 12.sp)
                                }
                            }
                            Text("A. Electrical Safety", fontWeight = FontWeight.Bold)
                            OutlinedTextField(value = powerConsumedByMandal, onValueChange = {
                                powerConsumedByMandal = it
                            }, placeholder = {
                                Text(text = "Power consumed by Mandal in Units")
                            }, label = {
                                Text(text = "Power consumed by Mandal in Units")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = sizeOfCableInstalled, onValueChange = {
                                sizeOfCableInstalled = it
                            }, placeholder = {
                                Text(text = "Size of Cable Installed in Sq.mm")
                            }, label = {
                                Text(text = "Size of Cable Installed in Sq.mm")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = typeOfCable, onValueChange = {
                                typeOfCable = it
                            }, placeholder = {
                                Text(text = "Type of Cable FRLS/ Non-FRLS")
                            }, label = {
                                Text(text = "Type of Cable FRLS/ Non-FRLS")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "MCB/ RCCB Installed Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedMcb by remember { mutableStateOf(false) }
                            var MCBInstalledYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedMcb, onExpandedChange = {
                                        expandedMcb = !expandedMcb
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = MCBInstalledYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedMcb
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedMcb,
                                        onDismissRequest = { expandedMcb = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    MCBInstalledYesorNo = item
                                                    expandedMcb = false
                                                })
                                        }
                                    }
                                }
                            }
                            OutlinedTextField(value = mcb_mccbInstalled, onValueChange = {
                                mcb_mccbInstalled = it
                            }, placeholder = {
                                Text(text = "MCB / RCCB installed")
                            }, label = {
                                Text(text = "MCB / RCCB installed")
                            }, modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(value = sourceOfBackupPowerSupply, onValueChange = {
                                sourceOfBackupPowerSupply = it
                            }, placeholder = {
                                Text(text = "Source of BackUp power")
                            }, label = {
                                Text(text = "Source of BackUp power")
                            }, modifier = Modifier.fillMaxWidth()
                            )

                            OutlinedTextField(value = earthingWireQuant, onValueChange = {
                                earthingWireQuant = it
                            }, placeholder = {
                                Text(text = "Earthing Wire Quantity")
                            }, label = {
                                Text(text = "Earthing Wire Quantity")
                            }, modifier = Modifier.fillMaxWidth()
                            )

                            var expanded by remember { mutableStateOf(false) }
                            var expanded1 by remember { mutableStateOf(false) }
                            var _24ElectricianYesOrNo by remember { mutableStateOf(yesOrNo[1]) }
                            Text(
                                text = "24x7 Electrician Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expanded, onExpandedChange = {
                                        expanded = !expanded
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = _24ElectricianYesOrNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    _24ElectricianYesOrNo = item
                                                    expanded = false
                                                })
                                        }
                                    }
                                }
                            }

                            var backupPowerSupplyYesOrNo by remember { mutableStateOf(yesOrNo[1]) }
                            var expandedbackupPowerSupply by remember { mutableStateOf(false) }

                            Text(
                                text = "BackUp Power Supply Yes/No", fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
//
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedbackupPowerSupply, onExpandedChange = {
                                        expandedbackupPowerSupply = !expandedbackupPowerSupply
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = backupPowerSupplyYesOrNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedbackupPowerSupply
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedbackupPowerSupply,
                                        onDismissRequest = { expandedbackupPowerSupply = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    backupPowerSupplyYesOrNo = item
                                                    expandedbackupPowerSupply = false
                                                })
                                        }
                                    }
                                }
                            }


                            var properTerminationOfCables by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Text(
                                text = "Proper Termination of Cables Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expanded1, onExpandedChange = {
                                        expanded1 = !expanded1
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = properTerminationOfCables,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expanded1
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expanded1,
                                        onDismissRequest = { expanded1 = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    properTerminationOfCables = item
                                                    expanded1 = false
                                                })
                                        }
                                    }
                                }
                            }
                            Text(
                                text = "B. Fire Extinguisher", fontWeight = FontWeight.Bold
                            )
                            var fireExtin1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireExtin2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireExt1Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireExt2Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "Fire Extinguishers Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedFireExtinguishers by remember { mutableStateOf(false) }
                            var FireExtinguishersYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedFireExtinguishers, onExpandedChange = {
                                        expandedFireExtinguishers = !expandedFireExtinguishers
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = FireExtinguishersYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedFireExtinguishers
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedFireExtinguishers,
                                        onDismissRequest = { expandedFireExtinguishers = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    FireExtinguishersYesorNo = item
                                                    expandedFireExtinguishers = false
                                                })
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = fireExtin1, onValueChange = {
                                    fireExtin1 = it
                                }, placeholder = {
                                    Text(text = "Fire Extinguisher (abc)")
                                }, label = {
                                    Text(text = "Fire Extinguisher (abc)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = fireExt1Quant, onValueChange = {
                                    fireExt1Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = fireExtin2, onValueChange = {
                                    fireExtin2 = it
                                }, placeholder = {
                                    Text(text = "Fire Extinguisher (co2)")
                                }, label = {
                                    Text(text = "Fire Extinguisher (co2)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = fireExt2Quant, onValueChange = {
                                    fireExt2Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            // C. Fire Detectors
                            Text(
                                text = "C. Fire Detector", fontWeight = FontWeight.Bold
                            )
                            var fireDetect1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireDetect2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireDetect1Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var fireDetect2Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }

                            Text(
                                text = "Fire Detectors Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedFireDetectors by remember { mutableStateOf(false) }
                            var FireDetectorsYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedFireDetectors, onExpandedChange = {
                                        expandedFireDetectors = !expandedFireDetectors
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = FireDetectorsYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedFireDetectors
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedFireDetectors,
                                        onDismissRequest = { expandedFireDetectors = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    FireDetectorsYesorNo = item
                                                    expandedFireDetectors = false
                                                })
                                        }
                                    }
                                }
                            }

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = fireDetect1, onValueChange = {
                                    fireDetect1 = it
                                }, placeholder = {
                                    Text(text = "Fire Detector (Detector)")
                                }, label = {
                                    Text(text = "Fire Detector (Detector)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = fireDetect1Quant, onValueChange = {
                                    fireDetect1Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = fireDetect2, onValueChange = {
                                    fireDetect2 = it
                                }, placeholder = {
                                    Text(text = "Fire Detector (MCP)")
                                }, label = {
                                    Text(text = "Fire Detector (MCP)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = fireDetect2Quant, onValueChange = {
                                    fireDetect2Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }

                            //D. CCTV System
                            Text(
                                text = "D. CCTV System", fontWeight = FontWeight.Bold
                            )
                            var typeOfCamera1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfCamera2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfCamera1Qty by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfCamera2Qty by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "CCTV Camera Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedCCTVCamera by remember { mutableStateOf(false) }
                            var CCTVCameraYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedCCTVCamera, onExpandedChange = {
                                        expandedCCTVCamera = !expandedCCTVCamera
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = CCTVCameraYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedCCTVCamera
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedCCTVCamera,
                                        onDismissRequest = { expandedCCTVCamera = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    CCTVCameraYesorNo = item
                                                    expandedCCTVCamera = false
                                                })
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfCamera1, onValueChange = {
                                    typeOfCamera1 = it
                                }, placeholder = {
                                    Text(text = "Type of Camera (Fixed)")
                                }, label = {
                                    Text(text = "Type of Camera (Fixed)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfCamera1Qty, onValueChange = {
                                    typeOfCamera1Qty = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfCamera2, onValueChange = {
                                    typeOfCamera2 = it
                                }, placeholder = {
                                    Text(text = "Type Of Camera (ptz)")
                                }, label = {
                                    Text(text = "Type of Camera (ptz)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfCamera2Qty, onValueChange = {
                                    typeOfCamera2Qty = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            var dvr by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var channels by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var dvrQuantity by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(value = dvr, onValueChange = {
                                    dvr = it
                                }, placeholder = {
                                    Text(text = "DVR/PVR")
                                }, label = {
                                    Text(text = "DVR/PVR")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = channels, onValueChange = {
                                    channels = it
                                }, placeholder = {
                                    Text(text = "Channels")
                                }, label = {
                                    Text(text = "Channels")
                                }, modifier = Modifier.weight(1.5f)
                                )
                                OutlinedTextField(value = dvrQuantity, onValueChange = {
                                    dvrQuantity = it
                                }, placeholder = {
                                    Text(text = "Qty")
                                }, label = {
                                    Text(text = "Qty")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            var storageDetails by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var monitoringMethodology by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            OutlinedTextField(value = storageDetails, onValueChange = {
                                storageDetails = it
                            }, placeholder = {
                                Text(text = "Storage Details")
                            }, label = {
                                Text(text = "Storage Details")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = monitoringMethodology, onValueChange = {
                                monitoringMethodology = it
                            }, placeholder = {
                                Text(text = "Monitoring Methodology (Realtime/Recorded)")
                            }, label = {
                                Text(text = "Monitoring Methodology (Realtime/Recorded)")
                            }, modifier = Modifier.fillMaxWidth()
                            )

                            // E. Public Address system
                            var typeOfSpeaker1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSpeaker2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSpeaker1Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSpeaker2Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var announcingTeam by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var announcingTeamQuant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "E. Public Address System", fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Public Address Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedPublicAddress by remember { mutableStateOf(false) }
                            var PublicAddressYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedPublicAddress, onExpandedChange = {
                                        expandedPublicAddress = !expandedPublicAddress
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = PublicAddressYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedPublicAddress
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedPublicAddress,
                                        onDismissRequest = { expandedPublicAddress = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    PublicAddressYesorNo = item
                                                    expandedPublicAddress = false
                                                })
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfSpeaker1, onValueChange = {
                                    typeOfSpeaker1 = it
                                }, placeholder = {
                                    Text(text = "Type of Speaker (Wall Mount)")
                                }, label = {
                                    Text(text = "Type of Speaker (Wall Mount)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfSpeaker1Quant, onValueChange = {
                                    typeOfSpeaker1Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfSpeaker2, onValueChange = {
                                    typeOfSpeaker2 = it
                                }, placeholder = {
                                    Text(text = "Type of Speaker (Ceiling Mount)")
                                }, label = {
                                    Text(text = "Type of Speaker (Ceiling Mount)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfSpeaker2Quant, onValueChange = {
                                    typeOfSpeaker2Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = announcingTeam, onValueChange = {
                                    announcingTeam = it
                                }, placeholder = {
                                    Text(text = "Announcing Team (Yes/No)")
                                }, label = {
                                    Text(text = "Announcing Team (Yes/No)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = announcingTeamQuant, onValueChange = {
                                    announcingTeamQuant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            //F. Metal and Bomb Detector
                            var typeOfDetector1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfDetector1Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfDetector2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfDetector2Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "F. Metal and Bomb Detector", fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Metal and Bomb Detector Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedMetalAndBomb by remember { mutableStateOf(false) }
                            var MetalAndBombYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedMetalAndBomb, onExpandedChange = {
                                        expandedMetalAndBomb = !expandedMetalAndBomb
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = MetalAndBombYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedMetalAndBomb
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedMetalAndBomb,
                                        onDismissRequest = { expandedMetalAndBomb = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    MetalAndBombYesorNo = item
                                                    expandedMetalAndBomb = false
                                                })
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfDetector1, onValueChange = {
                                    typeOfDetector1 = it
                                }, placeholder = {
                                    Text(text = "Type of Detector (Metal Detector)")
                                }, label = {
                                    Text(text = "Type of Detector (Metal Detector)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfDetector1Quant, onValueChange = {
                                    typeOfDetector1Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }

//                            Row(
//                                horizontalArrangement = Arrangement.spacedBy(4.dp)
//                            ) {
//                                OutlinedTextField(value = typeOfDetector2, onValueChange = {
//                                    typeOfDetector2 = it
//                                }, placeholder = {
//                                    Text(text = "Type of Detector (Bomb Detector)")
//                                }, label = {
//                                    Text(text = "Type of Detector (Bomb Detector)")
//                                }, modifier = Modifier.weight(2f)
//                                )
//                                OutlinedTextField(value = typeOfDetector2Quant, onValueChange = {
//                                    typeOfDetector2Quant = it
//                                }, placeholder = {
//                                    Text(text = "Quantity")
//                                }, label = {
//                                    Text(text = "Quantity")
//                                }, modifier = Modifier.weight(1f)
//                                )
//                            }
                            //G. Banners and First Aid Kit
                            var typeOfSign1 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSign1Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSign2 by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var typeOfSign2Quant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var firstAidKit by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var firstAidKitQuant by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "G. Banners and FirstAid Kit", fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "First Aid Kit Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedFirstAid by remember { mutableStateOf(false) }
                            var FirstAidYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedFirstAid, onExpandedChange = {
                                        expandedFirstAid = !expandedFirstAid
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = FirstAidYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedFirstAid
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedFirstAid,
                                        onDismissRequest = { expandedFirstAid = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    FirstAidYesorNo = item
                                                    expandedFirstAid = false
                                                })
                                        }
                                    }
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfSign1, onValueChange = {
                                    typeOfSign1 = it
                                }, placeholder = {
                                    Text(text = "Type of Sign Boards (Auto Glo)")
                                }, label = {
                                    Text(text = "Type of Sign Boards (Auto Glo)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfSign1Quant, onValueChange = {
                                    typeOfSign1Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = typeOfSign2, onValueChange = {
                                    typeOfSign2 = it
                                }, placeholder = {
                                    Text(text = "Type of Sign Boards (battery backed-up)")
                                }, label = {
                                    Text(text = "Type of Sign Boards (battery backed-up)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = typeOfSign2Quant, onValueChange = {
                                    typeOfSign2Quant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                OutlinedTextField(value = firstAidKit, onValueChange = {
                                    firstAidKit = it
                                }, placeholder = {
                                    Text(text = "FirstAid Kit (Yes/No)")
                                }, label = {
                                    Text(text = "FirstAid Kit (Yes/No)")
                                }, modifier = Modifier.weight(2f)
                                )
                                OutlinedTextField(value = firstAidKitQuant, onValueChange = {
                                    firstAidKitQuant = it
                                }, placeholder = {
                                    Text(text = "Quantity")
                                }, label = {
                                    Text(text = "Quantity")
                                }, modifier = Modifier.weight(1f)
                                )
                            }
                            //H. Other Measures Undertaken
                            var emergencyEvacuationPlanDetails by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var securityTeamDetails by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var trainingSecurityTeam by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "H. Other Measures Undertaken", fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Emergency Team Yes/No",
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.align(Alignment.Start)
                            )
                            var expandedEmergencyteam by remember { mutableStateOf(false) }
                            var EmergencyTeamYesorNo by remember {
                                mutableStateOf(yesOrNo[1])
                            }
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ExposedDropdownMenuBox(
                                    expanded = expandedEmergencyteam, onExpandedChange = {
                                        expandedEmergencyteam = !expandedEmergencyteam
                                    }, modifier = Modifier.fillMaxWidth()
                                ) {
                                    TextField(
                                        value = EmergencyTeamYesorNo,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedEmergencyteam
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(expanded = expandedEmergencyteam,
                                        onDismissRequest = { expandedEmergencyteam = false }) {
                                        yesOrNo.forEach { item ->
                                            DropdownMenuItem(text = { Text(text = item) },
                                                onClick = {
                                                    EmergencyTeamYesorNo = item
                                                    expandedEmergencyteam = false
                                                })
                                        }
                                    }
                                }
                            }

                            OutlinedTextField(
                                value = emergencyEvacuationPlanDetails,
                                onValueChange = {
                                    emergencyEvacuationPlanDetails = it
                                },
                                placeholder = {
                                    Text(text = "Emergency evacuation plan details")
                                },
                                label = {
                                    Text(text = "Emergency evacuation plan details")
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = securityTeamDetails, onValueChange = {
                                securityTeamDetails = it
                            }, placeholder = {
                                Text(text = "Security Team Details")
                            }, label = {
                                Text(text = "Security Team Details")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = trainingSecurityTeam, onValueChange = {
                                trainingSecurityTeam = it
                            }, placeholder = {
                                Text(text = "Training to Security Team")
                            }, label = {
                                Text(text = "Training to Security Team")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            // I. Remarks
                            var remarks by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            Text(
                                text = "I. Remarks", fontWeight = FontWeight.Bold
                            )
                            OutlinedTextField(value = remarks, onValueChange = {
                                remarks = it
                            }, placeholder = {
                                Text(text = "Remarks")
                            }, label = {
                                Text(text = "Remarks")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            var nameOfFSAIRepresentative by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            var nameOfMandalRepresentative by remember {
                                mutableStateOf(TextFieldValue(""))
                            }
                            OutlinedTextField(value = nameOfFSAIRepresentative, onValueChange = {
                                nameOfFSAIRepresentative = it
                            }, placeholder = {
                                Text(text = "Name Of FSAI Representative")
                            }, label = {
                                Text(text = "Name Of FSAI Representative")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            OutlinedTextField(value = nameOfMandalRepresentative, onValueChange = {
                                nameOfMandalRepresentative = it
                            }, placeholder = {
                                Text(text = "Name Of Mandal Representative")
                            }, label = {
                                Text(text = "Name Of Mandal Representative")
                            }, modifier = Modifier.fillMaxWidth()
                            )
                            var totalYesses = 0
                            AsyncImage(
                                model = uriImageState.value,
                                contentDescription = "",
                                error = painterResource(id = R.drawable.ic_launcher_background),
                                modifier = Modifier.size(220.dp)
                            )
                            var LatLngProgressstate by remember {
                                mutableStateOf(false)
                            }
                            Box() {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "Lat" + latitude.value)
                                    Text(text = "Long" + longitude.value)
                                }
                                androidx.compose.animation.AnimatedVisibility(
                                    visible = LatLngProgressstate, modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                ) {
                                    Box {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                            permissionLauncher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.RequestPermission()
                            ) { isGranted ->
                                isPermissionGranted = isGranted
                            }
                            Button(onClick = {
                                LatLngProgressstate = true
                                if (hasLocationPerms()) {
                                    if (isLocationEnabled()) {
                                        val fusedLocationPreviderClient =
                                            LocationServices.getFusedLocationProviderClient(this@RegisterMandalActivity)
                                        fusedLocationPreviderClient.getCurrentLocation(
                                            Priority.PRIORITY_BALANCED_POWER_ACCURACY, null
                                        ).addOnCompleteListener { task ->
                                            if (task.result != null) {
                                                LatLngProgressstate = false
                                                latitude.value = task.result.latitude.toString()
                                                longitude.value = task.result.longitude.toString()
                                                launcher.launch(
                                                    ImagePicker.with(this@RegisterMandalActivity)
                                                        .cameraOnly().createIntent()
                                                )
                                            } else {
                                                LatLngProgressstate = false
                                                Toast.makeText(
                                                    context,
                                                    "Some Error Occured While getting location ",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    } else {
                                        LatLngProgressstate = false
                                        Toast.makeText(
                                            this@RegisterMandalActivity,
                                            "PlEASE TURN ON YOUR GPS/LOCATION",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    LatLngProgressstate = false
                                    println("Permision not given")
//                                requestPerms(permissionLauncher)
                                    permissionLauncher.launch(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        options = ActivityOptionsCompat.makeBasic()
                                    )
                                    Toast.makeText(
                                        context,
                                        "Go to the Settings and give permissions to app, as you've permanently declined the permission",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }) {
                                Text(text = "Upload Image")
                            }
                            Button(onClick = {
                                if (!TextUtils.isEmpty(nameOfMandal.text) && !TextUtils.isEmpty(
                                        addOfMandal.text
                                    ) && !TextUtils.isEmpty(contactPerson.text) && !TextUtils.isEmpty(
                                        mobileNo.text
                                    ) && !TextUtils.isEmpty(totalAreaCovered.text) && !TextUtils.isEmpty(
                                        totalAreaOpen.text
                                    ) && !TextUtils.isEmpty(dateOfTechnicalEvaluation) && !TextUtils.isEmpty(
                                        powerConsumedByMandal.text
                                    ) && !TextUtils.isEmpty(sizeOfCableInstalled.text) && !TextUtils.isEmpty(
                                        typeOfCable.text
                                    ) && !TextUtils.isEmpty(mcb_mccbInstalled.text) && !TextUtils.isEmpty(
                                        _24ElectricianYesOrNo
                                    ) && !TextUtils.isEmpty(properTerminationOfCables) && !TextUtils.isEmpty(
                                        fireExtin1.text
                                    ) && !TextUtils.isEmpty(fireExt1Quant.text) && !TextUtils.isEmpty(
                                        fireExtin2.text
                                    ) && !TextUtils.isEmpty(fireExt2Quant.text) && !TextUtils.isEmpty(
                                        fireDetect1.text
                                    ) && !TextUtils.isEmpty(fireDetect1Quant.text) && !TextUtils.isEmpty(
                                        fireDetect2.text
                                    ) && !TextUtils.isEmpty(fireDetect2Quant.text) && !TextUtils.isEmpty(
                                        typeOfCamera1.text
                                    ) && !TextUtils.isEmpty(typeOfCamera1Qty.text) && !TextUtils.isEmpty(
                                        typeOfCamera2.text
                                    ) && !TextUtils.isEmpty(typeOfCamera2Qty.text) && !TextUtils.isEmpty(
                                        dvr.text
                                    ) && !TextUtils.isEmpty(channels.text) && !TextUtils.isEmpty(
                                        dvrQuantity.text
                                    ) && !TextUtils.isEmpty(storageDetails.text) && !TextUtils.isEmpty(
                                        monitoringMethodology.text
                                    ) && !TextUtils.isEmpty(typeOfSpeaker1.text) && !TextUtils.isEmpty(
                                        typeOfSpeaker1Quant.text
                                    ) && !TextUtils.isEmpty(typeOfSpeaker2.text) && !TextUtils.isEmpty(
                                        typeOfSpeaker2Quant.text
                                    ) && !TextUtils.isEmpty(announcingTeam.text) && !TextUtils.isEmpty(
                                        announcingTeamQuant.text
                                    ) && !TextUtils.isEmpty(typeOfDetector1.text) && !TextUtils.isEmpty(
                                        typeOfDetector1Quant.text
                                    ) && !TextUtils.isEmpty(typeOfSign1.text) && !TextUtils.isEmpty(
                                        typeOfSign1Quant.text
                                    ) && !TextUtils.isEmpty(typeOfSign2.text) && !TextUtils.isEmpty(
                                        typeOfSign2Quant.text
                                    ) && !TextUtils.isEmpty(firstAidKit.text) && !TextUtils.isEmpty(
                                        firstAidKitQuant.text
                                    ) && !TextUtils.isEmpty(emergencyEvacuationPlanDetails.text) && !TextUtils.isEmpty(
                                        securityTeamDetails.text
                                    ) && !TextUtils.isEmpty(trainingSecurityTeam.text) && !TextUtils.isEmpty(
                                        remarks.text
                                    ) && !TextUtils.isEmpty(nameOfFSAIRepresentative.text) && !TextUtils.isEmpty(
                                        nameOfMandalRepresentative.text
                                    ) && !TextUtils.isEmpty(MCBInstalledYesorNo) && !TextUtils.isEmpty(
                                        FireExtinguishersYesorNo
                                    ) && !TextUtils.isEmpty(FireDetectorsYesorNo) && !TextUtils.isEmpty(
                                        CCTVCameraYesorNo
                                    ) && !TextUtils.isEmpty(PublicAddressYesorNo) && !TextUtils.isEmpty(
                                        MetalAndBombYesorNo
                                    ) && !TextUtils.isEmpty(FirstAidYesorNo) && !TextUtils.isEmpty(
                                        mandalRepresentativeEmail.text
                                    ) && !TextUtils.isEmpty(EmergencyTeamYesorNo) && !TextUtils.isEmpty(
                                        uriImageState.value.toString()
                                    ) && uriImageState.value.toString()
                                        .isNotEmpty() && uriImageState.value.toString() != "null" && !TextUtils.isEmpty(
                                        latitude.value
                                    ) && !TextUtils.isEmpty(longitude.value)
                                ) {

                                    if (Patterns.EMAIL_ADDRESS.matcher(mandalRepresentativeEmail.text)
                                            .matches()
                                    ) {
                                        println(
                                            Patterns.EMAIL_ADDRESS.matcher(
                                                mandalRepresentativeEmail.toString()
                                            ).matches()
                                        )
                                        progressState = true
                                        val reference =
                                            storage.reference.child("images/{${nameOfMandal.text}}")
                                        val uniqueId = UUID.randomUUID().toString()
                                        reference.putFile(uriImageState.value!!)
                                            .addOnSuccessListener {
                                                reference.downloadUrl.addOnSuccessListener {
                                                    val downloadUri = it
                                                    val hashMap = HashMap<String, Any>()
                                                    hashMap["nameOfMandal"] = nameOfMandal.text
                                                    hashMap["addOfMandal"] = addOfMandal.text
                                                    hashMap["contactPerson"] = contactPerson.text
                                                    hashMap["mobileNo"] = mobileNo.text
                                                    hashMap["totalAreaCovered"] =
                                                        totalAreaCovered.text
                                                    hashMap["totalAreaOpen"] = totalAreaOpen.text
                                                    hashMap["dateOfTechnicalEvaluation"] =
                                                        dateOfTechnicalEvaluation
                                                    hashMap["powerConsumedByMandal"] =
                                                        powerConsumedByMandal.text
                                                    hashMap["sizeOfCableInstalled"] =
                                                        sizeOfCableInstalled.text
                                                    hashMap["typeOfCable"] = typeOfCable.text
                                                    hashMap["mcb_mccbInstalled"] =
                                                        mcb_mccbInstalled.text
                                                    hashMap["_24ElectricianYesOrNo"] =
                                                        _24ElectricianYesOrNo
                                                    hashMap["backupPowerSupplyYesOrNo"] = backupPowerSupplyYesOrNo
                                                    hashMap["sourceOfBackupPowerSupply"] = sourceOfBackupPowerSupply.text
                                                    hashMap["earthingWireQuant"] = earthingWireQuant.text
                                                    hashMap["properTerminationOfCables"] =
                                                        properTerminationOfCables
                                                    hashMap["fireExtin1"] = fireExtin1.text
                                                    hashMap["fireExt1Quant"] = fireExt1Quant.text
                                                    hashMap["fireExtin2"] = fireExtin2.text
                                                    hashMap["fireExt2Quant"] = fireExt2Quant.text
                                                    hashMap["fireDetect1"] = fireDetect1.text
                                                    hashMap["fireDetect1Quant"] =
                                                        fireDetect1Quant.text
                                                    hashMap["fireDetect2"] = fireDetect2.text
                                                    hashMap["fireDetect2Quant"] =
                                                        fireDetect2Quant.text
                                                    hashMap["typeOfCamera1"] = typeOfCamera1.text
                                                    hashMap["typeOfCamera1Qty"] =
                                                        typeOfCamera1Qty.text
                                                    hashMap["typeOfCamera2"] = typeOfCamera2.text
                                                    hashMap["typeOfCamera2Qty"] =
                                                        typeOfCamera2Qty.text
                                                    hashMap["dvr"] = dvr.text
                                                    //
                                                    hashMap["channels"] = channels.text
                                                    hashMap["dvrQuantity"] = dvrQuantity.text
                                                    hashMap["storageDetails"] = storageDetails.text
                                                    hashMap["monitoringMethodology"] =
                                                        monitoringMethodology.text
                                                    hashMap["typeOfSpeaker1"] = typeOfSpeaker1.text
                                                    hashMap["typeOfSpeaker1Quant"] =
                                                        typeOfSpeaker1Quant.text
                                                    hashMap["typeOfSpeaker2"] = typeOfSpeaker2.text
                                                    hashMap["typeOfSpeaker2Quant"] =
                                                        typeOfSpeaker2Quant.text
                                                    hashMap["announcingTeam"] = announcingTeam.text
                                                    hashMap["announcingTeamQuant"] =
                                                        announcingTeamQuant.text
                                                    hashMap["typeOfDetector1"] =
                                                        typeOfDetector1.text
                                                    hashMap["typeOfDetector1Quant"] =
                                                        typeOfDetector1Quant.text
                                                    hashMap["typeOfDetector2"] =
                                                        typeOfDetector2.text
                                                    hashMap["typeOfDetector2Quant"] =
                                                        typeOfDetector2Quant.text
                                                    hashMap["typeOfSign1"] = typeOfSign1.text
                                                    hashMap["typeOfSign1Quant"] =
                                                        typeOfSign1Quant.text
                                                    hashMap["typeOfSign2"] = typeOfSign2.text
                                                    hashMap["typeOfSign2Quant"] =
                                                        typeOfSign2Quant.text
                                                    hashMap["firstAidKit"] = firstAidKit.text
                                                    hashMap["firstAidKitQuant"] =
                                                        firstAidKitQuant.text
                                                    hashMap["emergencyEvacuationPlanDetails"] =
                                                        emergencyEvacuationPlanDetails.text
                                                    hashMap["securityTeamDetails"] =
                                                        securityTeamDetails.text
                                                    hashMap["trainingSecurityTeam"] =
                                                        trainingSecurityTeam.text
                                                    hashMap["remarks"] = remarks.text
                                                    hashMap["nameOfFSAIRepresentative"] =
                                                        nameOfFSAIRepresentative.text
                                                    hashMap["nameOfMandalRepresentative"] =
                                                        nameOfMandalRepresentative.text
                                                    hashMap["firstAuditStatus"] = false
                                                    hashMap["secondAuditStatus"] = false
                                                    hashMap["MCBInstalledYesorNo"] =
                                                        MCBInstalledYesorNo
                                                    hashMap["FireExtinguishersYesorNo"] =
                                                        FireExtinguishersYesorNo
                                                    hashMap["FireDetectorsYesorNo"] =
                                                        FireDetectorsYesorNo
                                                    hashMap["CCTVCameraYesorNo"] = CCTVCameraYesorNo
                                                    hashMap["PublicAddressYesorNo"] =
                                                        PublicAddressYesorNo
                                                    hashMap["MetalAndBombYesorNo"] =
                                                        MetalAndBombYesorNo
                                                    hashMap["FirstAidYesorNo"] = FirstAidYesorNo
                                                    hashMap["EmergencyTeamYesorNo"] =
                                                        EmergencyTeamYesorNo
                                                    hashMap["totalScore"] = ""
                                                    hashMap["nameOf1stAuditor"] = ""
                                                    hashMap["imagMandal"] = downloadUri.toString()
                                                    hashMap["latitude"] = latitude.value
                                                    hashMap["longitude"] = longitude.value
                                                    hashMap["finalAuditScore"] = ""
                                                    hashMap["mandalRepresentativeEmail"] =
                                                        mandalRepresentativeEmail.text
                                                    db.collection("mandals").document(uniqueId)
                                                        .set(hashMap).addOnSuccessListener {
                                                            progressState = false
                                                            Toast.makeText(
                                                                context,
                                                                "Mandal Registered Successfully",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                            progressState = true
                                                            if (MCBInstalledYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (FireExtinguishersYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (FireDetectorsYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (CCTVCameraYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (PublicAddressYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (MetalAndBombYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (FirstAidYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (EmergencyTeamYesorNo == "Yes") {
                                                                totalYesses++
                                                            }
                                                            if (totalYesses > 3 && MCBInstalledYesorNo == "Yes" && FireExtinguishersYesorNo == "Yes" && CCTVCameraYesorNo == "Yes") {
                                                                db.collection("mandalsSelectedForNext")
                                                                    .document(uniqueId).set(hashMap)
                                                                    .addOnSuccessListener {
                                                                        progressState = false
                                                                        Toast.makeText(
                                                                            context,
                                                                            "Mandal Qualified for next round",
                                                                            Toast.LENGTH_SHORT
                                                                        ).show()
                                                                        val intent = Intent(
                                                                            context,
                                                                            MainActivity::class.java
                                                                        )
                                                                        intent.flags =
                                                                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                                                        context.startActivity(intent)
                                                                    }
                                                            } else {
                                                                val intent = Intent(
                                                                    context,
                                                                    MainActivity::class.java
                                                                )
                                                                intent.flags =
                                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                                                context.startActivity(intent)
                                                            }
                                                        }
                                                }
                                            }
                                    } else {
                                        Toast.makeText(
                                            context, "Email Not Valid", Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        context, "Some Fields are Missing", Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }

                            ) {
                                Text(
                                    text = "Submit", fontSize = 18.sp, fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    AnimatedVisibility(visible = progressState) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                // A surface container using the 'background' color from the theme

            }
        }
    }

    private fun hasLocationPerms(): Boolean {
        return ContextCompat.checkSelfPermission(
            this@RegisterMandalActivity, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPerms(permissionLauncher: ManagedActivityResultLauncher<String, Boolean>) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//        permissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
    }

}
//@RequiresApi(Build.VERSION_CODES.N)
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    GaneshMandalTheme {
////        RegistrationForm(launcher, uriImageState)
//    }
//}