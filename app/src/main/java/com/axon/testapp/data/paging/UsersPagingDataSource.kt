package com.axon.testapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.axon.testapp.data.entities.User
import com.axon.testapp.data.remote.UserService
import retrofit2.HttpException
import java.io.IOException

class UsersPagingDataSource(
    private val userService: UserService,
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageNumber = params.key ?: 1

        return try {
            val response = userService.getAllUsers(pageNumber)
            val users = response.results

            LoadResult.Page(
                data = users,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (users.isEmpty()) null else pageNumber + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}