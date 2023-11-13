package com.wiprotest.domain.usecase

import android.content.Context
import com.wiprotest.domain.repository.Repository

class FetchUniversities (
    private val repository: Repository
) {
    operator fun invoke(context: Context, country: String) = repository.fetchUniversities(context, country)
}