// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("转换") }

    DesktopMaterialTheme() {
        Column {
            var decimalValue by remember { mutableStateOf("") }
            EditText("十进制", decimalValue, onValueChange = {
                decimalValue = it
            })

            var binaryValue by remember { mutableStateOf("") }
            EditText(
                label = "二进制",
                value = binaryValue,
                onValueChange = { },
            )

            var hexValue by remember { mutableStateOf("") }
            EditText(
                label = "十六进制",
                value = hexValue,
                onValueChange = { },
            )

            Button(
                onClick = {
                    decimalValue = decimalValue.toInt().toString(10)
                    binaryValue = decimalValue.toInt().toString(2)
                    hexValue = decimalValue.toInt().toString(16)
                },
                modifier = Modifier.size(150.dp, 60.dp).align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            ) {
                Text(text)
            }
        }
    }
}

@Composable
private fun EditText(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Black,
            disabledLabelColor = Color.DarkGray
        ),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(16.dp)
    )
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(width = Dp.Unspecified, height = Dp.Unspecified),
        title = "进制转换",
        icon = painterResource("images/icon.png")
    ) {
        App()
    }
}
