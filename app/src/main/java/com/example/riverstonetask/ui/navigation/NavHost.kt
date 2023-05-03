package com.example.riverstonetask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.riverstonetask.model.Product
import com.example.riverstonetask.ui.pages.ProductDetailsScreen
import com.example.riverstonetask.ui.pages.productlist.ProductListScreen

@Composable
fun MyNavHost(
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen(navController)
        }
        composable("product_details") {
            val product = it.arguments?.getParcelable<Product>("product_data")
            println("MyNavHost.product_details ")
            product?.let {
                println("MyNavHost.product_details 11 $product")
                ProductDetailsScreen(it)
            }
        }
    }
}
