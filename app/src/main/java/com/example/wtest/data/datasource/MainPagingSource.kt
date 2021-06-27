package com.example.wtest.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.wtest.data.entities.Zipcode
import com.example.wtest.repository.MainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MainPagingSource(private val zipCode: String = "", private val repository: MainRepository) :
    PagingSource<Int, Zipcode>() {

    private val result = mutableListOf<Zipcode>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Zipcode> {

        delay(3000)
        if (result.isEmpty()) {
            result.addAll(repository.getZipcodes().take(500))
        }

        val perPage = params.loadSize
        val currentPage = params.key ?: INITIAL_PAGE
        val toTake = perPage * currentPage

        val data = if (toTake > result.size && params.key != null) listOf() else result.take(toTake).takeLast(perPage)
        val nextKey = if (data.isEmpty()) null else currentPage + 1

        Log.d("--", "perPage: $perPage")
        Log.d("--", "currentPage: $currentPage")
        Log.d("--", "data size: ${data.size}")
        Log.d("--", "nextKey: $nextKey")

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