package com.spaceapp.presentation.space_news_detail

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: SpaceNewsDetailViewModel = hiltViewModel()
) {
    NewsDetailContent(modifier = modifier, newsUrl = viewModel.newsUrl)
}

@Composable
private fun NewsDetailContent(modifier: Modifier, newsUrl: String?) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        if (newsUrl != null) {
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(newsUrl)
                }
            }, update = {
                it.loadUrl(newsUrl)
            })
        }
    }
}