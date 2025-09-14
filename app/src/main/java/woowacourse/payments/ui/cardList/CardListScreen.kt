package woowacourse.payments.ui.cardList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cardList.components.CardListTopBar
import woowacourse.payments.ui.cardList.components.RegistrationCard
import woowacourse.payments.ui.cardRegister.components.PaymentCard
import woowacourse.payments.ui.common.model.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.FF000000
import woowacourse.payments.ui.theme.FF333333
import woowacourse.payments.ui.theme.Typography

@Composable
fun CardListScreen(
    cards: List<Card> = emptyList(),
    onRegistrationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                onRegistrationClick = { onRegistrationClick() },
                isShowRegistrationButton = cards.size > 1,
            )
        },
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = FF000000),
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (cards.isEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.add_new_card_title),
                    textAlign = TextAlign.Center,
                    color = FF333333,
                    style = Typography.displayLarge,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            cards.forEach { card ->
                Spacer(modifier = Modifier.height(36.dp))
                PaymentCard(card = card)
            }
            if (cards.size == 1 || cards.isEmpty()) {
                RegistrationCard(
                    onRegistrationClick = { onRegistrationClick() },
                    modifier = Modifier.padding(top = 36.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_EmptyCard() {
    AndroidpaymentsTheme {
        CardListScreen(onRegistrationClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_SingleCard() {
    AndroidpaymentsTheme {
        CardListScreen(
            cards =
                listOf(
                    Card(
                        number = "1111 - 2222 - **** - ****",
                        expiredDate = "04 / 21",
                        ownerName = "CREW",
                        password = "1234",
                    ),
                ),
            onRegistrationClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_MultipleCard() {
    AndroidpaymentsTheme {
        CardListScreen(
            cards =
                listOf(
                    Card(
                        number = "1111222233334444",
                        expiredDate = "0421",
                        ownerName = "CREW",
                        password = "1234",
                    ),
                    Card(
                        number = "1111222233334444",
                        expiredDate = "0421",
                        ownerName = "CREW",
                        password = "1234",
                    ),
                ),
            onRegistrationClick = {},
        )
    }
}
