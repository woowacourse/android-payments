@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    state: CardsUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CardsTopAppBar(state.cards.size > 1) },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            when {
                state.hasNoCard ->
                    NoCardContent(
                        addCard = {},
                        modifier = Modifier.fillMaxSize(),
                    )

                state.hasOneCard ->
                    OneCardContent(
                        card = state.cards.first(),
                        addCard = {},
                        modifier =
                            Modifier
                                .fillMaxSize(),
                    )

                state.hasMultipleCard ->
                    MultipleCardContent(
                        cards = state.cards,
                        modifier =
                            Modifier
                                .fillMaxSize(),
                    )
            }
        }
    }
}

@Preview
@Composable
private fun CardsScreenPreview(
    @PreviewParameter(CardsScreenPreviewParameterProvider::class) state: CardsUiState,
) {
    AndroidpaymentsTheme { CardsScreen(state) }
}

private class CardsScreenPreviewParameterProvider : PreviewParameterProvider<CardsUiState> {
    override val values: Sequence<CardsUiState> =
        sequenceOf(
            CardsUiState(emptyList()),
            CardsUiState(
                listOf(
                    Card(
                        number = "1234".repeat(4),
                        owner = "CREW",
                        expiredDate = "0421",
                    ),
                ),
            ),
            CardsUiState(
                listOf(
                    Card(
                        number = "1234".repeat(4),
                        owner = "CREW",
                        expiredDate = "0421",
                    ),
                    Card(
                        number = "1234".repeat(4),
                        owner = "CREW",
                        expiredDate = "0421",
                    ),
                    Card(
                        number = "1234".repeat(4),
                        owner = "CREW",
                        expiredDate = "0421",
                    ),
                ),
            ),
        )
}
