package com.example.myscreen.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun NewsScreen(newsViewModel: NewsViewModel, context: Context) {
    // Observe news articles using viewModel
    val newsState = newsViewModel.news.collectAsState()
    val news = newsState.value
    val selectedArticleUrl by newsViewModel.selectedArticleUrl.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
       Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = "Trending News",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
        )
        // Search bar
        TextField(
            value = searchQuery,
            onValueChange = { query ->
                searchQuery = query
                newsViewModel.searchNews(query)
            },
            label = { Text("Search News") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Pass the observed news articles to the content
        NewsContent(news = news, newsViewModel = newsViewModel)
        LaunchedEffect(selectedArticleUrl) {
            if (!selectedArticleUrl.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedArticleUrl))
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun NewsContent(news: List<Article>, newsViewModel: NewsViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(news) { article ->
            NewsItem(article = article, onItemClick = { newsViewModel.onArticleClicked(it.url) })
        }
    }
}
@Composable
fun NewsItem(article: Article, onItemClick: (Article) -> Unit) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isSelected = !isSelected
                onItemClick(article)
            }
            .padding(16.dp)
            .background(if (isSelected) green.copy(alpha = 0.2f) else Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .size(130.dp)
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

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 140.dp)
        ) {
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
