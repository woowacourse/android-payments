package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCards(
    cards: List<CardUiModel>,
    showTopAdd: Boolean,
    onAddCardClick: () -> Unit,
    onCardClick: (cardIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 32.dp),
    ) {
        if (cards.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.payment_add_new_card_prompt),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                )
                Spacer(Modifier.height(32.dp))
                AddCardButton(onClick = onAddCardClick)
            }
        } else {
            itemsIndexed(cards) { index, card ->
                PaymentCard(
                    card = card,
                    onClick = { onCardClick(index) },
                )
                if (index < cards.lastIndex) {
                    Spacer(Modifier.height(16.dp))
                }
            }
            if (!showTopAdd) {
                item {
                    Spacer(Modifier.height(24.dp))
                    AddCardButton(onClick = onAddCardClick)
                }
            }
        }
    }
}

@Preview(name = "카드 없음", showBackground = true)
@Composable
private fun PaymentCards_Empty_Preview() {
    AndroidpaymentsTheme {
        PaymentCards(
            cards = emptyList(),
            showTopAdd = false,
            onAddCardClick = {},
            onCardClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(name = "카드 1장", showBackground = true)
@Composable
private fun PaymentCards_One_Preview() {
    AndroidpaymentsTheme {
        PaymentCards(
            cards = listOf(sampleCard()),
            showTopAdd = false,
            onAddCardClick = {},
            onCardClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(name = "카드 3장", showBackground = true)
@Composable
private fun PaymentCards_Many_Preview() {
    AndroidpaymentsTheme {
        PaymentCards(
            cards = List(3) { sampleCard() },
            showTopAdd = true,
            onAddCardClick = {},
            onCardClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun sampleCard(): CardUiModel =
    CardUiModel(
        cardCompany = CardCompanyType.BC.toUiModel(),
        cardNumberRaw = "11112222********",
        expirationDateRaw = "1226",
        userName = "JOY",
        password = "1234",
    )
