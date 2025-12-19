package com.example.myphotos.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.myphotos.domain.model.ImageModel
import com.example.myphotos.ui.components.homeComponents.ImageCard
import com.example.myphotos.ui.components.homeComponents.TopAppBarHome
import com.example.myphotos.ui.viewmodel.ImagesViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ImageScreenRoute(viewModel: ImagesViewModel, id: Int, onNavBack: () -> Unit) {

    val state by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getImageById(id)
    }

    ImageCard(
        modifier = Modifier
            .fillMaxSize(),
        ctx = ctx,
        state = state.imageModel ?: ImageModel(
            0,
            "",
            "",
            ""
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = .4f)
            )
    )

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