package com.hzr.kemo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter4.loadState.LoadState
import com.hzr.kemo.ext.logd
import com.hzr.kemo.ext.loge
import com.hzr.kemo.model.BannerEntity
import com.hzr.kemo.model.DrinkListEntity
import com.hzr.kemo.repository.irepo.IDrinkListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val pageState: LoadState = LoadState.None,
    val drinkList: List<DrinkListEntity> = emptyList(),
    val carousels: List<BannerEntity> = emptyList()
)

sealed class HomeUiEvent {
    data class ShowToast(val msg: Int) : HomeUiEvent()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val drinkListRepository: IDrinkListRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchDrinkList()
        fetchCarouselPics()
    }

    /**
     * 获取主页奶茶列表
     */
    fun fetchDrinkList() {
        viewModelScope.launch {
            _uiState.update { it.copy(pageState = LoadState.Loading) }
            drinkListRepository.getDrinkList().onSuccess { result ->
                logd("获取到主页奶茶列表数据${result}")
                _uiState.update { it.copy(drinkList = result, pageState = LoadState.NotLoading.Complete) }
            }.onFailure { error ->
                loge(error)
                _uiState.update { it.copy(pageState = LoadState.Error(error)) }
            }
        }
    }

    /**
     * 获取主页轮播图列表
     */
    fun fetchCarouselPics() {
        viewModelScope.launch {
            _uiState.update { it.copy(pageState = LoadState.Loading) }
            drinkListRepository.getCarousePics().onSuccess { carousels ->
                logd("获取到轮播图列表${carousels}")
                _uiState.update { it.copy(carousels = carousels, pageState = LoadState.NotLoading.Complete) }
            }.onFailure { error ->
                loge(error)
                _uiState.update { it.copy(pageState = LoadState.Error(error)) }
            }
        }
    }
}