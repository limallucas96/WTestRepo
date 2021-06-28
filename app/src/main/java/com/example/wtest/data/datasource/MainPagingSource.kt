package com.example.wtest.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.repository.MainRepository
import kotlinx.coroutines.delay

class MainPagingSource(private val query: String, private val repository: MainRepository) :
    PagingSource<Int, Zipcode>() {

    private val result = mutableListOf<Zipcode>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Zipcode> {

        delay(1000) //Create UI delay so we can see the loading state in the bottom of recyclerview.

        if(result.isEmpty()) {
            if(query.isEmpty()) {
                result.addAll(repository.getZipcodes().take(500))
            } else {
                result.addAll(repository.getZipcodesLike(query))
            }
        }

        val perPage = params.loadSize
        val currentPage = params.key ?: INITIAL_PAGE
        val toTake = perPage * currentPage

        val data = if (toTake > result.size && params.key != null) listOf() else result.take(toTake).takeLast(perPage)
        val nextKey = if (data.isEmpty()) null else currentPage + 1

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Zipcode>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}