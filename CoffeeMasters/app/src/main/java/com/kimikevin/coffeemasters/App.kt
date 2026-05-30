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
import com.kimikevin.coffeemasters.pages.InfoPage
import com.kimikevin.coffeemasters.pages.MenuPage
import com.kimikevin.coffeemasters.pages.OffersPage
import com.kimikevin.coffeemasters.pages.OrderPage
import com.kimikevin.coffeemasters.ui.theme.CoffeeMastersTheme


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {
    val selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    CoffeeMastersTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        AppTitle()
                    }
                )
            },
            content = {
                when (selectedRoute.value) {
                    Routes.MenuPage.route ->
                        MenuPage(modifier = Modifier.padding(it))
                    Routes.OffersPage.route ->
                        OffersPage(modifier = Modifier.padding(it))
                    Routes.OrderPage.route ->
                        OrderPage(modifier = Modifier.padding(it))
                    Routes.InfoPage.route ->
                        InfoPage(modifier = Modifier.padding(it))
                }
            },
            bottomBar = {
                NavBar(
                    onChange = {
                        selectedRoute.value = it
                    },
                    selectedRoute = selectedRoute.value
                )
            }
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