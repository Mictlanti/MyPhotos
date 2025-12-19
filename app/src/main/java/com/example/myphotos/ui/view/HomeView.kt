package com.example.myphotos.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myphotos.ui.components.homeComponents.CardStyle
import com.example.myphotos.ui.components.homeComponents.TopAppBarHome
import com.example.myphotos.ui.viewmodel.ImagesViewModel

@Composable
fun HomeScreenRoute(viewModel: ImagesViewModel, onNavImage: (Int) -> Unit) {

    val state by viewModel.state.collectAsState()
    val ctx = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarHome("My photos")
        }
    ) { pad ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 600.dp),
            modifier = Modifier
                .padding(pad)
                .padding(15.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            itemsIndexed(state.listImages) { index, state ->
                CardStyle(
                    modifier = Modifier
                        .aspectRatio(1.8f),
                    ctx = ctx,
                    state = state,
                    onClick = { onNavImage(index + 1) }
                )
            }
        }
    }
}