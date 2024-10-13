package com.roxasjearom.coroutinexflow.ui.coroutinepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.coroutinexflow.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private fun Duration.toMilliseconds(): String =
        this.toLong(DurationUnit.MILLISECONDS).toString() + "ms"

}

data class SimpleCoroutineState(
    val isLoading: Boolean = false,
    val result: String? = null,
    val executionTime: String = "0ms",
)
