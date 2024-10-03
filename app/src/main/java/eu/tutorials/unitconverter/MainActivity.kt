package eu.tutorials.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                UnitConverterApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterApp() {
    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Meter") }
    var outputunit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var onExpanded by remember { mutableStateOf(false)}
    val converstionFactor = remember { mutableStateOf(1.00) }
    val oconverstionFactor = remember { mutableStateOf(1.00) }

//    val customTextStyle = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontSize = 16.sp,
//        color = Color.Red
//    )

    fun convertUnits(){
        // ?: - elvis operator
        val inputValue = inputvalue.toDoubleOrNull()?:0.0
        val result = (inputValue * converstionFactor.value*100.0 / oconverstionFactor.value).roundToInt()/100.0
        outputvalue = result.toString()

    }



    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter" , modifier = Modifier.padding(10.dp),
            style=MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedTextField(value = inputvalue ,
            onValueChange = {
            inputvalue = it
            convertUnits()
        },label={ Text("Enter Value")})

        Spacer(modifier = Modifier.padding(16.dp))

        Row {
        //input box
             Box {
                //input button
                Button(onClick = {iExpanded= true }) {
                    Text(inputunit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded=false}) {
                    DropdownMenuItem(text= {Text("Centimeter")},
                        onClick = {
                            iExpanded = false
                            inputunit = "Centimeter"
                            converstionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text= {Text("Meter")},
                        onClick = {
                            iExpanded = false
                            inputunit = "Meter"
                            converstionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text= {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputunit = "Feet"
                            converstionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text= {Text("Milimeter")},
                        onClick = {
                            iExpanded = false
                            inputunit = "Milimeter"
                            converstionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(30.dp))
        //output box button
            Box {
                Button(onClick = { onExpanded = true}) {
                    Text(outputunit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = onExpanded, onDismissRequest = {onExpanded = false}) {
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = {
                            onExpanded = false
                            outputunit = "Centimeter"
                            oconverstionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = {
                            onExpanded = false
                            outputunit = "Meter"
                            oconverstionFactor.value = 1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Feet") },
                        onClick = {
                            onExpanded = false
                            outputunit = "Feet"
                            oconverstionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Milimeter") },
                        onClick = {
                            onExpanded = false
                            outputunit = "Milimeter"
                            oconverstionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.padding(16.dp))
        // Add more conversion logic and UI elements here
        Text("result : $outputvalue $outputunit",
            style = MaterialTheme.typography.headlineMedium)
    }

}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverterApp()

}