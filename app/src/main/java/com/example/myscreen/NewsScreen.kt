package com.example.myscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.myscreen.ui.theme.green


@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    // Observe news articles using viewModel
    val newsState = newsViewModel.news.collectAsState()
    val news = newsState.value

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Trending News",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        // Pass the observed news articles to the content
        NewsContent(news = news) { /* Handle item click if needed */ }
    }
}

@Composable
fun NewsContent(news: List<Article>, onItemClick: (Article) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(news) { article ->
            NewsItem(article, onItemClick)
        }
    }
}

@Composable
fun NewsItem(article: Article, onItemClick: (Article) -> Unit) {
    var isSelected by remember { mutableStateOf(false) }
    val previouslySelected = remember { mutableStateOf<Article?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (isSelected) {
                    isSelected = false
                    previouslySelected.value = null
                } else {
                    isSelected = true
                    previouslySelected.value?.let { previouslySelectedItem ->
                        isSelected = false
                    }
                    previouslySelected.value = article
                }
                onItemClick(article)
            }
            .padding(16.dp)
            .background(if (isSelected) green.copy(alpha = 0.2f) else Color.Transparent)
    ){
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        ) {
            article.urlToImage?.let { imageUrl ->
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),

                )
            }
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 140.dp)
        ) {
            Column {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (isSelected) Color.Black else Color.Black
                )
                article.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isSelected) Color.Black else Color.Gray
                    )
                    Text(
                        text = article.url,
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isSelected) Color.Black else Color.Black
                    )
                }
            }
        }
    }
}

