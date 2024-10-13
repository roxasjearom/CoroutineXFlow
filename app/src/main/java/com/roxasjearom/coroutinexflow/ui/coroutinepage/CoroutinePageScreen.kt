package com.roxasjearom.coroutinexflow.ui.coroutinepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.roxasjearom.coroutinexflow.R

@Composable
fun CoroutinePageScreen(
    modifier: Modifier = Modifier,
    viewModel: CoroutinePageViewModel
) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        SimpleCoroutineSection(
            title = stringResource(id = R.string.simple_coroutine_title),
            description = stringResource(id = R.string.simple_coroutine_description),
            state = viewModel.simpleCoroutineState.collectAsState().value,
            onButtonClick = {
                viewModel.startSimpleCoroutine()
            }
        )

        SimpleCoroutineSection(
            title = stringResource(R.string.sequential_coroutine_title),
            description = stringResource(R.string.sequential_coroutine_description),
            state = viewModel.sequentialCoroutineState.collectAsState().value,
            onButtonClick = {
                viewModel.startSequentialCoroutines()
            }
        )

        SimpleCoroutineSection(
            title = stringResource(R.string.asynchronous_coroutines_title),
            description = stringResource(R.string.asynchronous_coroutines_description),
            state = viewModel.asyncCoroutineState.collectAsState().value,
            onButtonClick = {
                viewModel.startAsyncCoroutines()
            }
        )
    }
}
