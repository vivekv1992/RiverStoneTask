package com.example.riverstonetask.ui.pages.productlist

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.riverstonetask.model.Product
import com.example.riverstonetask.network.Resource
import org.koin.androidx.compose.koinViewModel


@Composable
fun ProductListScreen(navController: NavController, bookListViewModel : BookListViewModel) {
    val products by bookListViewModel.myStateFlow.collectAsStateWithLifecycle()
    val state: Resource<List<Product>> = products
    if (state is Resource.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.Blue)
        }
    } else if (state is Resource.Failure) {
        Text(text = state.message)
    } else if (state is Resource.Success) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = state.data) { item ->
                val card = Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("product_details?product=${item.id}")
                        }) {
                    Column {
                        ThumbnailImage(item.thumbnail)
                        Text(
                            text = item.title,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700
                        )
                        Text(
                            text = item.description,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "From ${item.price.toInt()}",
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700

                        )
                    }
                }
            }
        }

    }

}

@Composable
fun ThumbnailImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(url).crossfade(true).build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentScale = ContentScale.Crop
    )
}
