package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.model.CardsUiState
import woowacourse.payments.ui.debug.fixture.cardUiModelSample
import woowacourse.payments.ui.debug.fixture.cardUiModelSamples

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    cardsUiState: CardsUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.card_categories_title),
            )
        },
        actions = {
            when (cardsUiState) {
                is CardsUiState.Success -> {
                    when (cardsUiState.content) {
                        is CardsUiState.Success.Content.Multiple -> CreateCardButton(onAddClick)
                        CardsUiState.Success.Content.None -> null
                        is CardsUiState.Success.Content.Single -> null
                    }
                }
            }
        },
        modifier = modifier.padding(4.dp, 18.dp),
    )
}

@Composable
fun CreateCardButton(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(onClick = onAddClick, modifier) {
        Text(
            stringResource(R.string.add_message),
            color = Color.Black,
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoneNewCardsTopBarPreview() {
    CardsTopBar(CardsUiState.Success(CardsUiState.Success.Content.None), {})
}

@Preview(showBackground = true)
@Composable
fun SingleNewCardsTopBarPreview() {
    CardsTopBar(CardsUiState.Success(CardsUiState.Success.Content.Single(cardUiModelSample)), {})
}

@Preview(showBackground = true)
@Composable
fun MultipleNewCardsTopBarPreview() {
    CardsTopBar(
        CardsUiState.Success(CardsUiState.Success.Content.Multiple(cardUiModelSamples)),
        {})
}
