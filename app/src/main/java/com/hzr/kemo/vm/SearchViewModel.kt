package com.hzr.kemo.vm

import androidx.lifecycle.ViewModel
import com.chad.library.adapter4.loadState.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SearchUiState(
    val pageLoad: LoadState = LoadState.None,
    val searchText: String = "",
    val results: List<Any> = emptyList(),
)

@HiltViewModel
class SearchViewModel @Inject constructor(

 ) : ViewModel() {
    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState = _searchUiState.asStateFlow()

    fun fetchSearchResults(searchText: String) {
        _searchUiState.update { it.copy(pageLoad = LoadState.Loading) }

    }
}