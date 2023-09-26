package com.summer.passwordcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.summer.passwordcompose.ui.theme.PasswordComposeTheme
import com.summer.passwordcompose.ui.theme.spacingLarge
import com.summer.passwordcompose.ui.theme.spacingSmall
import com.summer.passwordcompose.ui.theme.spacingXSmall
import com.summer.passwordcompose.utils.AppKeyboardFocusManager
import com.summer.passwordcompose.utils.preview.DarkLightPreviews

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppKeyboardFocusManager()
            PasswordComposeTheme {
                MainScreenView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@DarkLightPreviews
@Composable
fun MainScreenView(modifier: Modifier = Modifier) {
    val cont = LocalContext.current
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            var text by remember { mutableStateOf("") }
            Text(
                modifier = modifier.padding(horizontal = spacingXSmall),
                text = LocalContext.current.getString(R.string.create_account),
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = modifier.padding(horizontal = spacingXSmall),
                text = LocalContext.current.getString(R.string.create_your_account_to_continue),
                //style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacingXSmall, vertical = spacingLarge),
                value = text,
                label = { Text(text = LocalContext.current.getString(R.string.name)) },
                onValueChange = {
                    text = it
                },

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User Name"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
            )
            Button(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = spacingXSmall, vertical = spacingSmall), onClick = {
                Toast.makeText(cont, "HELLO", Toast.LENGTH_LONG).show()
            }) {
                Text(text = LocalContext.current.getString(R.string.next))
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@DarkLightPreviews
@Composable
fun GreetingPreview() {
    PasswordComposeTheme {
        Greeting("Android")
    }
}