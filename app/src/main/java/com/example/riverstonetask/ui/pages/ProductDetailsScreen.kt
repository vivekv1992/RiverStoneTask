package com.example.riverstonetask.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riverstonetask.model.Product
import com.example.riverstonetask.ui.pages.productlist.ThumbnailImage


@Composable
fun ProductDetailsScreen(product: Product) {
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