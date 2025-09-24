package woowacourse.payments.ui.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.cardlist.components.CardListTopBar
import woowacourse.payments.ui.common.components.PaymentCard
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel

@Composable
fun CardListScreen(
    cards: List<CardUiModel>,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiModel) -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = onAddCardClick,
                showAddButton = cards.size > 1,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(top = 12.dp)
                    .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            if (cards.isEmpty()) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.add_card_message),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            cards.forEach { card: CardUiModel ->
                PaymentCard(card = card, onClick = { onCardClick(card) })
            }
            if (cards.size <= 1) {
                AddPaymentCard(onClick = onAddCardClick)
            }
        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview(
    @PreviewParameter(CardListScreenPreviewParameterProvider::class) cards: List<CardUiModel>,
) {
    CardListScreen(cards = cards, onAddCardClick = {})
}

private class CardListScreenPreviewParameterProvider : PreviewParameterProvider<List<CardUiModel>> {
    private val cardCompany: CardCompanyUiModel =
        CardCompanyUiModel(
            name = R.string.bc_card,
            logo = R.drawable.bc,
            color = 0xFFF04651,
        )
    private val card: CardUiModel =
        CardUiModel(
            cardCompany = cardCompany,
            number = "1111 - 2222 - 3333 - 4444",
            expirationDate = "09 / 25",
            holderName = "CREW",
        )

    override val values: Sequence<List<CardUiModel>> =
        sequenceOf(
            emptyList(),
            listOf(card),
            List(3) { card },
        )
}
