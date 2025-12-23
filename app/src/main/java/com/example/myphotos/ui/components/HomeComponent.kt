package com.example.myphotos.ui.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myphotos.domain.model.ImageModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(
    title: String,
    navIcon: @Composable (() -> Unit)? = null,
    action: @Composable (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(title, fontSize = 26.sp)
        },
        navigationIcon = { if (navIcon != null) navIcon() },
        actions = { if(action != null) action() },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun CardStyle(
    modifier: Modifier,
    ctx: Context,
    state: ImageModel,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.large
    ) {
        Box(modifier = modifier) {
            ImageCard(
                modifier = Modifier
                    .fillMaxSize(),
                ctx = ctx,
                state = state
            )
            Text(
                state.title,
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ImageCard(modifier: Modifier, ctx: Context, state: ImageModel) {

    val imageRequest = ImageRequest
        .Builder(ctx)
        .data(state.contentUrl)
        .listener(
            onError = { _, result ->
                Log.e("Coil", "Error loading image", result.throwable)
            }
        )
        .crossfade(enable = true)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = state.title,
        contentScale = ContentScale.Crop
    )
}