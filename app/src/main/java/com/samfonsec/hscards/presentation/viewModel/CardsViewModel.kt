package com.samfonsec.hscards.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samfonsec.hscards.domain.model.Card
import com.samfonsec.hscards.domain.response.DataResponse
import com.samfonsec.hscards.domain.useCase.GetCardsUseCase
import com.samfonsec.hscards.domain.useCase.GetClassesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class CardsViewModel constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val getClassesUseCase: GetClassesUseCase
) : ViewModel() {

    private val _onClassesResult = MutableLiveData<List<String>>()
    val onClassesResult: LiveData<List<String>> = _onClassesResult

    private val _onCardsResult = MutableLiveData<List<Card>>()
    val onCardsResult: LiveData<List<Card>> = _onCardsResult

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean> = _onLoading

    private val _onError = MutableLiveData<Boolean>()
    val onError: LiveData<Boolean> = _onError

    private var currentJob: Job? = null

    var selectedTab = 0

    fun getClasses() = viewModelScope.launch {
        _onLoading.value = true
        getClassesUseCase.execute().let { result ->
            when (result) {
                is DataResponse.Success -> _onClassesResult.value = result.data
                is DataResponse.Error -> _onError.value = false
            }
        }
    }

    fun getCards(className: String) {
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            _onLoading.value = true
            getCardsUseCase.execute(className).let { result ->
                when (result) {
                    is DataResponse.Success -> _onCardsResult.value = result.data
                    is DataResponse.Error -> _onError.value = result.exception.isCanceling()
                }
            }
            _onLoading.value = false
        }
    }

    private fun Throwable.isCanceling() = this is CancellationException
}
