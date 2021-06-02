package com.axon.testapp.data

import androidx.paging.PagingSource
import com.axon.testapp.api.UsersApi
import retrofit2.HttpException
import java.io.IOException

class UsersPagingSource(
    private val usersApi: UsersApi,
    private val results: Int
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageNumber = params.key ?: 1

        return try {
            val response = usersApi.getUsers(results, pageNumber)
            val users = response.results

            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (users.isNotEmpty()) pageNumber + 1 else null

            LoadResult.Page(
                data = users,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}