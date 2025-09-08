package woowacourse.payments.ui.features.cardlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.features.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.features.cardlist.components.PaymentsTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Black700

@Composable
fun CardListScreen(onAddCard: () -> Unit) {
    val paymentCardList = remember { mutableStateListOf<PaymentCard>() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentsTopBar(
                onAddClick = onAddCard,
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
            if (paymentCardList.isEmpty()) {
                // 카드가 없을 때
                Text(
                    text = stringResource(R.string.card_list_add_payment_card_title),
                    fontSize = 18.sp,
                    letterSpacing = 0.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black700,
                )
                Spacer(modifier = Modifier.height(32.dp))
                AddPaymentCard(onClick = onAddCard)
            } else if (paymentCardList.size == 1) {
                // 카드가 하나일 때
                PaymentCard(
                    paymentCard = paymentCardList[0],
                )
            } else {
                // 카드가 2장 이상일 떄
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(paymentCardList) { card ->
                        PaymentCard(
                            paymentCard = card,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenPreview() {
    AndroidpaymentsTheme {
        CardListScreen({})
    }
}
