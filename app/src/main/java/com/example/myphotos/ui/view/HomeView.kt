package com.example.myphotos.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myphotos.ui.components.CardStyle
import com.example.myphotos.ui.components.TopAppBarHome
import com.example.myphotos.ui.viewmodel.ImagesViewModel

@Composable
fun HomeScreenRoute(viewModel: ImagesViewModel, onNavImage: (Long) -> Unit, navAddImageView: () -> Unit) {

    val state by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadImages()
    }

    Scaffold(
        topBar = {
            TopAppBarHome("My photos")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navAddImageView() }
            ) {
                Icon(
                    Icons.Default.Add,
                    "Add image"
                )
            }
        }
    ) { pad ->
        if(state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 600.dp),
                modifier = Modifier
                    .padding(pad)
                    .padding(15.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.listImages) { state ->
                    CardStyle(
                        modifier = Modifier
                            .aspectRatio(1.8f),
                        ctx = ctx,
                        state = state,
                        onClick = { onNavImage(state.localId) }
                    )
                }
            }
        }
    }
}