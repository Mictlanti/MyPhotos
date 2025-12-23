package com.example.myphotos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.mappers.toDomain
import com.example.myphotos.data.mappers.toImageDomainEntity
import com.example.myphotos.domain.repository.ImageRepository
import com.example.myphotos.domain.util.Result
import com.example.myphotos.ui.state.ImageContentState
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

    private val _state = MutableStateFlow(ImageContentState())
    val state : StateFlow<ImageContentState> = _state.asStateFlow()

    fun loadImages() {
        viewModelScope.launch {
            _state.update { state -> state.copy(isLoading = true) }
            when(val res = imageRepository.getImages()) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        errorMsg = res.msg,
                        listImages = res.data.orEmpty().toImageDomainEntity().reversed(),
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    _state.value = _state.value.copy(
                        errorMsg = res.msg,
                        listImages = res.data.orEmpty().toImageDomainEntity(),
                        isLoading = true
                    )
                }
            }
        }
    }

    fun getImageById(id: Long) {
        viewModelScope.launch {
            _state.update { state -> state.copy(isLoading = true) }
            when(val res = imageRepository.getImageById(id)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        errorMsg = res.msg,
                        imageModel = res.data?.toDomain(),
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    _state.value = _state.value.copy(
                        errorMsg = res.msg,
                        imageModel = res.data?.toDomain(),
                        isLoading = true
                    )
                }
            }
        }
    }

    fun insertImage(
        title: String,
        imageUrl: String,
        description: String
    ) {

        val newImage = ImageEntity(
            remoteId = null,
            title = title,
            descriptionText = description,
            contentUrl = imageUrl
        )

        viewModelScope.launch {
            imageRepository.createImage(newImage)
        }
    }

}