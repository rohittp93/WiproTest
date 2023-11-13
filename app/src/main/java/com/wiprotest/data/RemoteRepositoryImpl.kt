package com.wiprotest.data

import android.content.Context
import android.widget.Toast
import com.wiprotest.domain.model.Response
import com.wiprotest.domain.model.University
import com.wiprotest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteRepositoryImpl @Inject constructor() : Repository {

    override fun fetchUniversities(context: Context, country: String): Flow<Response<List<University>>> =
        flow {
            emit(Response.Loading)
            val apiService = ApiService.getInstance()
            try {
                val universityListResult = apiService.fetchUniversities(country)
                emit(Response.Success(universityListResult))
            } catch (e: Exception) {
                emit(Response.Error(e.message ?: e.toString()))
            }
        }
}