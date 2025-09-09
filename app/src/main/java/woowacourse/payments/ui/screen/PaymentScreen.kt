package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import woowacourse.payments.domain.model.CardInfo
import woowacourse.payments.ui.components.AddCardComponent
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.PaymentTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen(
    cards: List<CardInfo>,
    onAddCardClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentTopBar(
                modifier = Modifier.fillMaxWidth(),
                onAddCardClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(32.dp))

            when {
                cards.isEmpty() -> EmptyCardContent(onAddCardClick = onAddCardClick)
                else -> MultipleCardsContent(cards = cards, onAddCardClick = onAddCardClick)
            }
        }
    }
}

@Composable
private fun EmptyCardContent(onAddCardClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.payment_add_new_card_prompt),
            fontSize = 18.sp,
            fontWeight = FontWeight(700),
        )
        Spacer(Modifier.height(32.dp))
        AddCardComponent(
            onClick = onAddCardClick,
        )
    }
}

@Composable
private fun MultipleCardsContent(
    cards: List<CardInfo>,
    onAddCardClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(cards) { cardInfo ->
            PaymentCard(cardInfo = cardInfo)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            if (cards.size < 3) {
                AddCardComponent(
                    onClick = onAddCardClick,
                )
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
                            CardInfo(
                                "1111-2222-3333-4444",
                                "04/21",
                                "CREW",
                                "1234",
                            ),
                        ),
                    onAddCardClick = {},
                )
            }
            Box(Modifier.height(400.dp)) {
                PaymentScreen(
                    cards =
                        listOf(
                            CardInfo("1111-2222-3333-4444", "04/21", "CREW", "1234"),
                            CardInfo("5555-6666-7777-8888", "05/22", "GAHYUN", "5678"),
                            CardInfo("9999-0000-1111-2222", "06/23", "ANDY", "9012"),
                        ),
                    onAddCardClick = {},
                )
            }
        }
    }
}
