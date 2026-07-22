package com.hzr.kemo.repository.irepo

import com.hzr.kemo.model.SearchResultEntity

interface ISearchRepository {
    /**
     * 获取搜索结果
     */
    suspend fun getSearchResults(searchText: String): Result<List<SearchResultEntity>>
}