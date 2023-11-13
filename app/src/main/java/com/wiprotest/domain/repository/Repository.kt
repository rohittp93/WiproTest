package com.wiprotest.domain.repository

import android.content.Context
import com.wiprotest.domain.model.Response
import com.wiprotest.domain.model.University
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchUniversities(context: Context, country: String): Flow<Response<List<University>>>
}