package com.example.myphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myphotos.ui.navigation.NavGraph
import com.example.myphotos.ui.theme.MyPhotosTheme
import com.example.myphotos.ui.view.HomeScreenRoute
import com.example.myphotos.ui.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val imageViewModel : ImagesViewModel = viewModel()

            MyPhotosTheme {
                NavGraph(viewModel = imageViewModel)
            }
        }
    }
}

