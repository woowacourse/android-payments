package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.components.AddCardComponent
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.PaymentTopBar
import woowacourse.payments.ui.mapper.toUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen(
    cards: List<CardUiModel>,
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentTopBar(
                modifier = Modifier.fillMaxWidth(),
                onAddClick = onAddCardClick.takeIf { cards.size >= 3 },
            )
        },
    ) { innerPadding ->
        PaymentBody(
            cards = cards,
            onAddCardClick = onAddCardClick,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        )
    }
}

@Composable
private fun PaymentBody(
    cards: List<CardUiModel>,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))

        if (cards.isEmpty()) {
            Text(
                text = stringResource(R.string.payment_add_new_card_prompt),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
            )
            Spacer(Modifier.height(32.dp))
            AddCardComponent(onClick = onAddCardClick)
        } else {
            cards.forEachIndexed { index, card ->
                PaymentCard(card = card)
                if (index < cards.lastIndex) {
                    Spacer(Modifier.height(16.dp))
                }
            }
            if (cards.size < 3) {
                Spacer(Modifier.height(24.dp))
                AddCardComponent(onClick = onAddCardClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentScreenPreview() {
    AndroidpaymentsTheme {
        Column {
            Box(Modifier.height(300.dp)) {
                PaymentScreen(cards = emptyList(), onAddCardClick = {})
            }
            Box(Modifier.height(300.dp)) {
                PaymentScreen(
                    cards =
                        listOf(
                            Card("1111222233334444", "0421", "CREW", "1234").toUiModel(),
                        ),
                    onAddCardClick = {},
                )
            }
            Box(Modifier.height(400.dp)) {
                PaymentScreen(
                    cards =
                        listOf(
                            Card("1111222233334444", "0421", "CREW", "1234"),
                            Card("5555666677778888", "0522", "GAHYUN", "5678"),
                            Card("9999000011112222", "0623", "ANDY", "9012"),
                        ).map { it.toUiModel() },
                    onAddCardClick = {},
                )
            }
        }
    }
}
