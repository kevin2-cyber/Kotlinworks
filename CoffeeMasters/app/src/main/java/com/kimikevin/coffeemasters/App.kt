package com.kimikevin.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kimikevin.coffeemasters.ui.theme.CoffeeMastersTheme

@Preview
@Composable
private fun AppPreview() {
    CoffeeMastersTheme {
        App()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AppTitle()
                }
            )
        },
        bottomBar = {
            NavBar(
                onChange = {
                    selectedRoute.value = it
                },
                selectedRoute = selectedRoute.value
            )
        }
    ) { innerPadding ->
        OffersPage(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AppTitle() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Coffee Masters Logo"
        )
    }
}