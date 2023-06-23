package com.elewa.sampleandroidapp.modules.home.presentation.viewmodel


import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elewa.sampleandroidapp.R
import com.elewa.sampleandroidapp.core.expections.NetworkException
import com.elewa.sampleandroidapp.core.expections.ServerException
import com.elewa.sampleandroidapp.core.expections.UnknownException
import com.elewa.sampleandroidapp.modules.home.domain.interceptors.GetCurrentPricePeriodicUseCase
import com.elewa.sampleandroidapp.modules.home.domain.interceptors.GetCurrentPriceUseCase
import com.elewa.sampleandroidapp.modules.home.presentation.mapper.toUiModel
import com.elewa.sampleandroidapp.modules.home.presentation.uimodel.CoinDeskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinDeskViewModel @Inject constructor(
    private val getCurrentPriceUseCase: GetCurrentPriceUseCase,
    private val getCurrentPricePeriodicUseCase: GetCurrentPricePeriodicUseCase
) : ViewModel() {

    private val _coinDeskMutable = MutableStateFlow<CoinDeskState>(CoinDeskState())
    val coinDeskMutable: StateFlow<CoinDeskState>
        get() = _coinDeskMutable

    private val _error = MutableSharedFlow<Int>()
    val error: SharedFlow<Int>
        get() = _error

    init {
        getCurrentPrice()
        getCurrentPricePeriodicUseCase()
    }

    fun getCurrentPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            _coinDeskMutable.emit(CoinDeskState(isLoading = true))
            try {
                getCurrentPriceUseCase.invoke(Unit).fold({
                    _coinDeskMutable.emit(CoinDeskState(data = it.toUiModel()))
                }, {
                    it.handleError()
                })

            } catch (e: IOException) {
                e.handleError()
            } catch (e: Exception) {
                e.handleError()
            }

        }
    }

    private fun getCurrentPricePeriodicUseCase() {
        viewModelScope.launch(Dispatchers.IO) {
            _coinDeskMutable.emit(CoinDeskState(isLoading = true))
            try {
                getCurrentPricePeriodicUseCase.invoke(60000).collect {
                    it.fold({
                        _coinDeskMutable.emit(CoinDeskState(data = it.toUiModel()))
                    }, {
                        it.handleError()
                    })
                }
            } catch (e: IOException) {
                e.handleError()
            } catch (e: Exception) {
                e.handleError()
            }

        }
    }

    private fun updateError(@StringRes message: Int) {
        viewModelScope.launch {
            _error.emit(message)
        }
    }

    private fun Throwable.handleError() {
        when (this@handleError) {
            is NetworkException -> updateError(R.string.no_internet)
            is ServerException -> updateError(R.string.server_error)
            is UnknownException -> updateError(R.string.unknown_error)
            else -> updateError(R.string.unknown_error)
        }
    }


}