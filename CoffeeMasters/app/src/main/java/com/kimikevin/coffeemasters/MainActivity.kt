package com.kimikevin.coffeemasters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kimikevin.coffeemasters.ui.theme.CoffeeMastersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeMastersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstComposable()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstComposable() {
    var name = remember {
        mutableStateOf("")
    }
    Column() {
        Text(
            text = "Hello ${name.value}",
            modifier = Modifier
                .padding(8.dp)
                .background(Color.Yellow)
                .padding(16.dp)
        )
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            }
        )
    }

}
