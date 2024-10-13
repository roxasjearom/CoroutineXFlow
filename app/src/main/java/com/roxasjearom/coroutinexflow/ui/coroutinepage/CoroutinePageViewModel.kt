package com.roxasjearom.coroutinexflow.ui.coroutinepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.coroutinexflow.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.measureTime

@HiltViewModel
class CoroutinePageViewModel @Inject constructor(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _simpleCoroutineState: MutableStateFlow<SimpleCoroutineState> =
        MutableStateFlow(SimpleCoroutineState())
    val simpleCoroutineState: StateFlow<SimpleCoroutineState> = _simpleCoroutineState.asStateFlow()

    private val _sequentialCoroutineState: MutableStateFlow<SimpleCoroutineState> =
        MutableStateFlow(SimpleCoroutineState())
    val sequentialCoroutineState: StateFlow<SimpleCoroutineState> = _sequentialCoroutineState.asStateFlow()

    private val _asyncCoroutineState: MutableStateFlow<SimpleCoroutineState> =
        MutableStateFlow(SimpleCoroutineState())
    val asyncCoroutineState: StateFlow<SimpleCoroutineState> = _asyncCoroutineState.asStateFlow()

    fun startSimpleCoroutine() {
        viewModelScope.launch {
            val timeTaken = measureTime {
                _simpleCoroutineState.update {
                    it.copy(
                        isLoading = true,
                        executionTime = ""
                    )
                }

                val data = dataRepository.fetchSmallData()

                _simpleCoroutineState.update {
                    it.copy(
                        result = data,
                        isLoading = false
                    )
                }
            }
            _simpleCoroutineState.update {
                it.copy(executionTime = timeTaken.toMilliseconds())
            }
        }
    }

    fun startSequentialCoroutines() {
        viewModelScope.launch {
            val timeTaken = measureTime {
                _sequentialCoroutineState.update {
                    it.copy(
                        isLoading = true,
                        executionTime = ""
                    )
                }

                val smallData = dataRepository.fetchSmallData()
                val mediumData = dataRepository.fetchMediumData()

                _sequentialCoroutineState.update {
                    it.copy(
                        result = smallData + mediumData,
                        isLoading = false
                    )
                }
            }
            _sequentialCoroutineState.update {
                it.copy(executionTime = timeTaken.toMilliseconds())
            }
        }
    }

    fun startAsyncCoroutines() {
        viewModelScope.launch {
            val timeTaken = measureTime {
                _asyncCoroutineState.update {
                    it.copy(
                        isLoading = true,
                        executionTime = ""
                    )
                }

                val deferredSmallData = async { dataRepository.fetchSmallData() }
                val deferredMediumData = async { dataRepository.fetchMediumData() }
                val result = deferredSmallData.await() + deferredMediumData.await()

                _asyncCoroutineState.update {
                    it.copy(
                        result = result,
                        isLoading = false
                    )
                }
            }
            _asyncCoroutineState.update {
                it.copy(executionTime = timeTaken.toMilliseconds())
            }
        }
    }

    private fun Duration.toMilliseconds(): String =
        this.toLong(DurationUnit.MILLISECONDS).toString() + "ms"

}

data class SimpleCoroutineState(
    val isLoading: Boolean = false,
    val result: String? = null,
    val executionTime: String = "0ms",
)
