package com.example.myphotos.ui.view

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PhotoAlbum
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myphotos.ui.components.TopAppBarHome
import com.example.myphotos.ui.viewmodel.ImagesViewModel

@Composable
fun AddImageScreenRoute(viewModel: ImagesViewModel, onNavBack: () -> Unit) {

    val textFieldList = remember { mutableStateListOf("", "") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            uri?.let {
                context.contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                selectedImageUri = it
            }
        }

    selectedImageUri?.let { uri ->
        Image(
            painter = rememberAsyncImagePainter(uri),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background.copy(alpha = .5f)
                ),
            contentScale = ContentScale.Crop
        )
    }

    Scaffold(
        topBar = {
            TopAppBarHome(
                "Add Image",
                navIcon = {
                    IconButton(onClick = onNavBack) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            "Nav back"
                        )
                    }
                },
                action = {
                    IconButton(
                        onClick = {
                            if(selectedImageUri != null && textFieldList.first().isNotEmpty() && textFieldList.last().isNotEmpty()) {
                                viewModel.insertImage(
                                    title = textFieldList.first(),
                                    imageUrl = selectedImageUri?.toString() ?: "",
                                    description = textFieldList.last()
                                )
                                onNavBack()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Default.Save,
                            "Save"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    imagePickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
            ) {
                Icon(
                    Icons.Default.PhotoAlbum,
                    "Add photo"
                )
            }
        },
        containerColor = if(selectedImageUri == null) MaterialTheme.colorScheme.background else Color.Transparent
    ) { pad ->
        AddImageScreen(
            modifier = Modifier
                .padding(pad)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            textFieldList = textFieldList
        )
    }

}

@Composable
private fun AddImageScreen(modifier: Modifier, textFieldList: MutableList<String>) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        textFieldList.forEachIndexed { index, string ->
            TextField(
                value = string,
                onValueChange = { newText ->
                    textFieldList[index] = newText
                },
                label = { Text(if(index == 0) "Title" else "Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(if(index == 0).5f else 5f),
                colors = TextFieldDefaults.colors(
                    disabledLabelColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .5f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .5f),
                    disabledContainerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = .5f)
                )
            )
        }
    }
}