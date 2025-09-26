package woowacourse.payments.ui.cards

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cards.component.CardsTopAppBar
import woowacourse.payments.ui.cards.component.RegistrationBox
import woowacourse.payments.ui.cards.state.CardsUiState
import woowacourse.payments.ui.cards.state.CardsViewModel
import woowacourse.payments.ui.common.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun CardsScreen(
    onRegistrationClick: () -> Unit,
    onCardClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardsViewModel = remember { CardsViewModel() },
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CardsTopAppBar(
                onRegistrationClick = onRegistrationClick,
                isVisibleRegistrationButton = uiState is CardsUiState.Multiple,
            )
        },
    ) { innerPadding ->
        CardsScreenContent(
            uiState = uiState,
            onRegistrationClick = onRegistrationClick,
            onCardClick = onCardClick,
            modifier =
                modifier
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun CardsScreenContent(
    uiState: CardsUiState,
    onRegistrationClick: () -> Unit,
    onCardClick: (CardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is CardsUiState.Empty -> {
                RegistrationGuideText()
                RegistrationBox(onRegistrationClick)
            }

            is CardsUiState.Single -> {
                PaymentCard(
                    card = uiState.card,
                    modifier = Modifier.clickable { onCardClick(uiState.card) },
                )
                Spacer(modifier = Modifier.height(10.dp))
                RegistrationBox(onRegistrationClick)
            }

            is CardsUiState.Multiple -> {
                uiState.cards.forEach { card: CardUiModel ->
                    PaymentCard(
                        card = card,
                        modifier = Modifier.clickable { onCardClick(card) },
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
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
//
// @Preview(showBackground = true, name = "데이터 존재 X")
// @Composable
// private fun NoContentPreview() {
//    val uiState = CardsScreenUiState(emptyList())
//    CardsScreen(
//        onRegistrationClick = {},
//        uiState = uiState,
//    )
// }
//
// @Preview(showBackground = true, name = "데이터 1개 존재")
// @Composable
// private fun HasOneContentPreview() {
//    val uiState =
//        CardsScreenUiState(
//            listOf(
//                CardUiModel(
//                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
//                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
//                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
//                ),
//            ),
//        )
//
//    AndroidpaymentsTheme {
//        CardsScreen(
//            onRegistrationClick = {},
//            uiState = uiState,
//        )
//    }
// }
//
// @Preview(showBackground = true, name = "데이터 2개 이상 존재")
// @Composable
// private fun HasMultipleContentPreview() {
//    val uiState =
//        CardsScreenUiState(
//            listOf(
//                CardUiModel(
//                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
//                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
//                    cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
//                ),
//                CardUiModel(
//                    cardholderNameUiModel = CardholderNameUiModel("CREW ABCDEFGHIJK"),
//                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
//                    cardExpirationDateUiModel = CardExpirationDateUiModel("1188"),
//                ),
//            ),
//        )
//    AndroidpaymentsTheme {
//        CardsScreen(
//            onRegistrationClick = {},
//            uiState = uiState,
//        )
//    }
// }
