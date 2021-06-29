package com.example.wtest.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.repository.MainRepository
import kotlinx.coroutines.delay

class MainPagingSource(private val query: String, private val repository: MainRepository) :
    PagingSource<Int, Zipcode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Zipcode> {

        delay(1000) //Create UI delay so we can see the loading state in the bottom of recyclerview.

        val limitPerPage = params.loadSize
        val currentPage = params.key ?: INITIAL_PAGE
        val offSet = limitPerPage * currentPage

        val result = if (query.isEmpty()) {
            repository.getZipcodes(limitPerPage, offSet)
        } else {
            repository.getZipcodesLike(limitPerPage, offSet, query)
        }

        val nextKey = if (result.isEmpty()) null else currentPage + 1

        Log.d("..", "limitPerPage $limitPerPage offSet $offSet dataSize ${result.size} nextKey $nextKey")

        return LoadResult.Page(
            data = result,
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
        private const val INITIAL_PAGE = 0
    }
}