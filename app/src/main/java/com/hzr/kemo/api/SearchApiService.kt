package com.hzr.kemo.api

import com.hzr.kemo.model.SearchResultEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class SearchApiService @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getSearchResults(searchText: String): KemoResponse<List<SearchResultEntity>>{
        return httpClient.get(ApiPath.SEARCH_RESULT_LIST) {
            parameter("searchText", searchText)
        }.body()
    }

}