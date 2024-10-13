package com.roxasjearom.coroutinexflow.ui.coroutinepage

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roxasjearom.coroutinexflow.R
import com.roxasjearom.coroutinexflow.ui.theme.CoroutineXFlowTheme

@Composable
fun SimpleCoroutineSection(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    state: SimpleCoroutineState,
    onButtonClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (state.isLoading) {
                Text(
                    text = stringResource(R.string.loading_label),
                    style = MaterialTheme.typography.bodyMedium,
                )
            } else {
                Text(
                    text = if (state.result.isNullOrEmpty()) "" else "Execution time is ${state.executionTime}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(32.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Button(onClick = onButtonClick) {
                    Text(text = stringResource(R.string.simple_coroutine_button_label))
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SimpleCoroutineSectionPreview(modifier: Modifier = Modifier) {
    CoroutineXFlowTheme {
        SimpleCoroutineSection(
            title = stringResource(id = R.string.simple_coroutine_title),
            description = stringResource(id = R.string.simple_coroutine_description),
            state = SimpleCoroutineState(),
            onButtonClick = {}
        )
    }
}
