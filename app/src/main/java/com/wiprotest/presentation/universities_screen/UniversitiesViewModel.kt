package com.wiprotest.presentation.universities_screen


import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.wiprotest.domain.model.Response
import com.wiprotest.domain.model.University
import com.wiprotest.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversitiesViewModel @Inject constructor(private val useCase: UseCases, application: Application) : AndroidViewModel(application) {
    var checkRefreshState = mutableStateOf(false)

    private val _universityResponseState = mutableStateOf<Response<List<University>>>(Response.Idle)
    val universityResponseState: State<Response<List<University>>> = _universityResponseState

    fun fetchUniversities(context: Context, country: String) {
        viewModelScope.launch {
            useCase.fetchUniversities(context, country).collect { response ->
                _universityResponseState.value = response
            }
        }
    }
}