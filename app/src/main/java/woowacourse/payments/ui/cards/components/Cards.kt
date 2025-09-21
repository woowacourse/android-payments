package woowacourse.payments.ui.cards.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.cards.CardsStateHolder
import woowacourse.payments.ui.component.CardImage
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.ExpirationDateUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun Cards(
    scrollState: ScrollState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardsStateHolder: CardsStateHolder = remember { CardsStateHolder() },
) {
    Column(
        modifier =
            modifier
                .padding(top = 12.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        cardsStateHolder.cardList.forEach { card ->
            CardImage(
                bankType = card.bankType,
                cardNumber = card.cardNumber.value,
                cardHolder = card.cardHolder.value,
                expirationDate = card.expirationDate.value,
            )
        }

        if (cardsStateHolder.isAddableWithAddCard()) {
            if (cardsStateHolder.isEmpty()) {
                Text(
                    text = stringResource(R.string.cards_no_card),
                )
            }
            AddCardImage {
                onAddClick()
            }
        }
    }
}

@Preview(showBackground = true, name = "카드가 없을 때")
@Composable
private fun CardsPreview_NoCards() {
    Cards(
        scrollState = rememberScrollState(),
        onAddClick = {},
    )
}

@Preview(showBackground = true, name = "카드가 하나 있을 때")
@Composable
private fun CardsPreview_OneCard_AddButtonVisible() {
    val sampleCard =
        PaymentCardUiModel(
            bankType = BankType.KAKAO,
            cardNumber = CardNumberUiModel("1234123412341234"),
            expirationDate = ExpirationDateUiModel("1225"),
            cardHolder = CardHolderUiModel("김환노"),
        )
    Cards(
        scrollState = rememberScrollState(),
        onAddClick = {},
        cardsStateHolder = CardsStateHolder(listOf(sampleCard)),
    )
}

@Preview(showBackground = true, name = "카드가 여러 개 있을 때 (추가 버튼 숨김)")
@Composable
private fun CardsPreview_MultipleCards_AddButtonHidden() {
    val sampleCards =
        listOf(
            PaymentCardUiModel(
                bankType = BankType.KAKAO,
                cardNumber = CardNumberUiModel("1234123412341234"),
                expirationDate = ExpirationDateUiModel("0611"),
                cardHolder = CardHolderUiModel("김환노"),
            ),
            PaymentCardUiModel(
                bankType = BankType.KB,
                cardNumber = CardNumberUiModel("1234123412341234"),
                expirationDate = ExpirationDateUiModel("0511"),
                cardHolder = CardHolderUiModel("김공백"),
            ),
        )
    Cards(
        scrollState = rememberScrollState(),
        onAddClick = {},
        cardsStateHolder = CardsStateHolder(sampleCards),
    )
}
