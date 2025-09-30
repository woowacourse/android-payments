package woowacourse.payments.view.cardaddition.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.view.cardaddition.CardAdditionUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAdditionTopAppBar(
    checkEnabled: Boolean,
    onUiEvent: (CardAdditionUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.card_addition_top_bar_title)) },
        navigationIcon = {
            IconButton(onClick = { onUiEvent(CardAdditionUiEvent.NavigateBack) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.card_addition_top_bar_navigate_back_content_description),
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onUiEvent(CardAdditionUiEvent.AddCard) },
                enabled = checkEnabled,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.card_addition_top_bar_done_content_description),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CardAdditionTopBarPreview(
    @PreviewParameter(CardAdditionTopBarPreviewParameterProvider::class) completable: Boolean,
) {
    CardAdditionTopAppBar(
        checkEnabled = completable,
        onUiEvent = {},
    )
}

private class CardAdditionTopBarPreviewParameterProvider : CollectionPreviewParameterProvider<Boolean>(listOf(true, false))
