package com.example.myphotos.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myphotos.ui.state.ImageContentState

@Composable
fun ErrorScreen(modifier: Modifier, state: ImageContentState) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Something grog", fontSize = 27.sp)
        Icon(
            Icons.Default.Error,
            "Error",
            modifier = Modifier.size(100.dp)
        )
        state.errorMsg?.let { s ->
            Text(s)
        }
    }
}