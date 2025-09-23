package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.component.CardsTopAppBar
import woowacourse.payments.ui.cards.component.RegistrationBox
import woowacourse.payments.ui.cards.state.CardsScreenUiState
import woowacourse.payments.ui.common.component.PaymentCard
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    onRegistrationClick: () -> Unit,
    uiState: CardsScreenUiState,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CardsTopAppBar(
                onRegistrationClick = onRegistrationClick,
                isVisibleRegistrationButton = uiState.isVisibleRegistrationButtonInTopBar(),
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            uiState = uiState,
            onRegistrationClick = onRegistrationClick,
            modifier =
                modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun CardsScreenContent(
    uiState: CardsScreenUiState,
    onRegistrationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.hasNoContent()) {
            RegistrationGuideText()
        }

        uiState.value.forEach { card: CardUiModel ->
            PaymentCard(card = card)
            Spacer(modifier = Modifier.height(20.dp))
        }

        if (uiState.isVisibleRegistrationBoxInContent()) {
            RegistrationBox(onRegistrationClick)
        }
    }
}

@Composable
private fun RegistrationGuideText() {
    Text(
        text = stringResource(R.string.cards_screen_registration_guide),
        modifier = Modifier.fillMaxWidth(),
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true, name = "데이터 존재 X")
@Composable
private fun NoContentPreview() {
    val uiState = CardsScreenUiState(emptyList())
    CardsScreen(
        onRegistrationClick = {},
        uiState = uiState,
    )
}

@Preview(showBackground = true, name = "데이터 1개 존재")
@Composable
private fun HasOneContentPreview() {
    val uiState =
        CardsScreenUiState(
            listOf(
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
                ),
            ),
        )

    AndroidpaymentsTheme {
        CardsScreen(
            onRegistrationClick = {},
            uiState = uiState,
        )
    }
}

@Preview(showBackground = true, name = "데이터 2개 이상 존재")
@Composable
private fun HasMultipleContentPreview() {
    val uiState =
        CardsScreenUiState(
            listOf(
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
                ),
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW ABCDEFGHIJK"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("1188"),
                ),
            ),
        )
    AndroidpaymentsTheme {
        CardsScreen(
            onRegistrationClick = {},
            uiState = uiState,
        )
    }
}
