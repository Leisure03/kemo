package com.hzr.kemo.repository

import com.hzr.kemo.api.SearchApiService
import com.hzr.kemo.ext.unwrapToEntity
import com.hzr.kemo.model.SearchResultEntity
import com.hzr.kemo.repository.irepo.ISearchRepository
import jakarta.inject.Inject

class SearchRepository @Inject constructor(
    private val searchApiService: SearchApiService
): ISearchRepository {
    override suspend fun getSearchResults(searchText: String): Result<List<SearchResultEntity>> {
        return runCatching { searchApiService.getSearchResults(searchText) }.unwrapToEntity()
    }
}