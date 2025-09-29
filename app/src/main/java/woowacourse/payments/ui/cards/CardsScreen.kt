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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import toDomain
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardRepository
import woowacourse.payments.ui.cards.component.CardsTopAppBar
import woowacourse.payments.ui.cards.component.RegistrationBox
import woowacourse.payments.ui.cards.state.CardsUiState
import woowacourse.payments.ui.cards.state.CardsViewModel
import woowacourse.payments.ui.common.component.PaymentCard
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardsScreen(
    onRegistrationClick: () -> Unit,
    onCardClick: (CardUiModel) -> Unit,
    viewModel: CardsViewModel,
    modifier: Modifier = Modifier,
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

@Preview(showBackground = true, name = "데이터 존재 X")
@Composable
private fun NoContentPreview() {
    val viewModel = CardsViewModel(CardsFixture(emptyList()), CardsUiState.Empty)
    CardsScreen(
        onRegistrationClick = { },
        onCardClick = {},
        viewModel = viewModel,
    )
}

@Preview(showBackground = true, name = "데이터 1개 존재")
@Composable
private fun HasOneContentPreview() {
    val card =
        CardUiModel(
            cardCompanyUiModel = CardCompanyUiModel.BC,
            cardholderNameUiModel = CardholderNameUiModel("CREW"),
            cardNumberUiModel = CardNumberUiModel("1111222233334444"),
            cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
        )
    val viewModel =
        CardsViewModel(
            CardsFixture(listOf(card.toDomain())),
            CardsUiState.Single(card),
        )

    AndroidpaymentsTheme {
        CardsScreen(
            onRegistrationClick = { },
            onCardClick = {},
            viewModel = viewModel,
        )
    }
}

@Preview(showBackground = true, name = "데이터 2개 이상 존재")
@Composable
private fun HasMultipleContentPreview() {
    val cards =
        listOf(
            CardUiModel(
                cardCompanyUiModel = CardCompanyUiModel.HYUNDAE,
                cardholderNameUiModel = CardholderNameUiModel("CREW"),
                cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                cardExpirationDateUiModel = CardExpirationDateUiModel("1299"),
            ),
            CardUiModel(
                cardCompanyUiModel = CardCompanyUiModel.KAKAO,
                cardholderNameUiModel = CardholderNameUiModel("CN"),
                cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                cardExpirationDateUiModel = CardExpirationDateUiModel("1188"),
            ),
        )
    val viewModel =
        CardsViewModel(
            CardsFixture(cards.map { it.toDomain() }),
            CardsUiState.Multiple(cards),
        )

    AndroidpaymentsTheme {
        CardsScreen(
            onRegistrationClick = { },
            onCardClick = {},
            viewModel = viewModel,
        )
    }
}

private data class CardsFixture(
    private val cards: List<Card>,
) : CardRepository {
    override fun findAll(): List<Card> = cards

    override fun add(card: Card) {}

    override fun update(
        cardId: Long,
        updateCard: Card,
    ) {
    }

    override fun findById(cardId: Long): Card = cards[0]
}
