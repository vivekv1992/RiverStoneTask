package com.example.riverstonetask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.riverstonetask.ui.pages.ProductDetailsScreen
import com.example.riverstonetask.ui.pages.productlist.BookListViewModel
import com.example.riverstonetask.ui.pages.productlist.ProductListScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyNavHost(
    navController: NavHostController) {
    val bookListViewModel = koinViewModel<BookListViewModel>()

    NavHost(
        navController = navController,
        startDestination = "product_list") {

        composable("product_list") {
            ProductListScreen(navController, bookListViewModel)
        }
        composable("product_details?product={productId}",
            arguments = listOf(navArgument("productId") { defaultValue = 1 })) {
            val productId = it.arguments?.getInt("productId")
            productId?.let {
                ProductDetailsScreen(productId, bookListViewModel)
            }
        }
    }
}
