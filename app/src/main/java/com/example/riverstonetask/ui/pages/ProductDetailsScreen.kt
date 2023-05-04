package com.example.riverstonetask.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.riverstonetask.model.Product
import com.example.riverstonetask.network.Resource
import com.example.riverstonetask.ui.pages.productlist.BookListViewModel
import com.example.riverstonetask.ui.pages.productlist.ThumbnailImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProductDetailsScreen(productId: Int, bookListViewModel : BookListViewModel ) {
    val product =  bookListViewModel.getProductbyId(productId)
    if (product == null) {
        Text(text = "No data available")
    } else {
        Column {
            Text(
                text = product.title,
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
            Text(
                text = product.description,
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )
            Text(
                text = "From ${product.price.toInt()}",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.W700

            )
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = product.images) { item ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),

                        ) {
                        ThumbnailImage(item)
                    }
                }
            }

        }
    }
}
