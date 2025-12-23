package com.example.myphotos.ui.view

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myphotos.ui.components.ImageCard
import com.example.myphotos.ui.components.TopAppBarHome
import com.example.myphotos.ui.state.ImageContentState
import com.example.myphotos.ui.viewmodel.ImagesViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ImageScreenRoute(viewModel: ImagesViewModel, id: Long, onNavBack: () -> Unit) {

    val state by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    LaunchedEffect(id) {
        viewModel.getImageById(id)
    }

    if(!state.isLoading && state.errorMsg == null) {
        BackgroundImageView(state, ctx)
    }

    Scaffold(
        topBar = {
            TopAppBarHome(
                state.imageModel?.title ?: "",
                navIcon = {
                    IconButton(onClick = onNavBack) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            "Nav Back"
                        )
                    }
                }
            )
        },
        containerColor = Color.Transparent
    ) { pad ->
        if(state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }
        else {
            when {
                state.errorMsg != null -> {
                    ErrorScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(pad)
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            MarkdownText(
                                markdown = state.imageModel?.descriptionText ?: "",
                                linkColor = MaterialTheme.colorScheme.onBackground,
                                isTextSelectable = true,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BackgroundImageView(state: ImageContentState, ctx: Context) {
    state.imageModel?.let { image ->
        ImageCard(
            modifier = Modifier
                .fillMaxSize(),
            ctx = ctx,
            state = image
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = .4f)
            )
    )
}