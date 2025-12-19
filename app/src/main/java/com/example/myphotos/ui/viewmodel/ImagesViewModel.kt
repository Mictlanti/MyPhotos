package com.example.myphotos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myphotos.data.mappers.toImageDomainEntity
import com.example.myphotos.domain.repository.ImageRepository
import com.example.myphotos.domain.state.ImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ImageState())
    val state : StateFlow<ImageState> = _state.asStateFlow()

    init {
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { state ->
                state.copy(
                    listImages = imageRepository.getImages().toImageDomainEntity()
                )
            }
        }
    }

    fun getImageById(id: Int) {
        val image = _state.value.listImages.firstOrNull {
            it.id == id
        }

        if(image != null) {
            _state.update { state ->
                state.copy(
                    imageModel = image
                )
            }
        }

    }

}