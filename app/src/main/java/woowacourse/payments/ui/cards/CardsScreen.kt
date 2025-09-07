package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.cards.components.AddCardImage
import woowacourse.payments.ui.cards.components.CardItem
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun CardsScreen(innerPadding: PaddingValues) {
    val cardList =
        rememberSaveable { mutableStateListOf<PaymentCardUiModel>() }

    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .padding(top = 12.dp)
                .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        cardList.forEach { card ->
            CardItem(
                paymentCard = card,
            )
        }

        if (cardList.size <= 1) {
            if (cardList.isEmpty()) {
                Text(
                    text = "새로운 카드를 등록해주세요",
                )
            }
            AddCardImage()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardScreenPreview() {
    CardsScreen(innerPadding = PaddingValues())
}
