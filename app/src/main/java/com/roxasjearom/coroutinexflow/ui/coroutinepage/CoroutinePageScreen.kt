package com.roxasjearom.coroutinexflow.ui.coroutinepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

@Composable
fun CoroutinePageScreen(
    modifier: Modifier = Modifier,
    viewModel: CoroutinePageViewModel
) {
    Column(modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        SimpleCoroutineSection(
            state = viewModel.simpleCoroutineState.collectAsState().value,
            onButtonClick = {
                viewModel.startSimpleCoroutine()
            }
        )
    }
}
