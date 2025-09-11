package woowacourse.payments.ui.features.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.features.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.features.cardlist.components.PaymentsTopBar
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Black700
import java.time.YearMonth

@Composable
fun CardListScreen(
    onAddCard: () -> Unit,
    paymentCardList: List<PaymentCard> = emptyList(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentsTopBar(
                onAddClick = onAddCard,
                isAddButtonVisible = paymentCardList.size > 1,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 카드가 없을 때만 안내 문구 표시
            if (paymentCardList.isEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = stringResource(R.string.card_list_add_payment_card_title),
                    fontSize = 18.sp,
                    letterSpacing = 0.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = Black700,
                )
                Spacer(modifier = Modifier.height(32.dp))
            } else {
                // 카드가 1장 이상일 때 카드 목록 표시
                Spacer(modifier = Modifier.height(12.dp))
                for (card in paymentCardList) {
                    PaymentCard(paymentCard = card)
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }

            // 카드가 한장 이하일 때만 '카드 추가' 패널 표시
            if (paymentCardList.size <= 1) {
                AddPaymentCard(modifier = Modifier.clickable { onAddCard() })
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenEmptyPreview() {
    AndroidpaymentsTheme {
        CardListScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenOneCardPreview() {
    val dummyPaymentCard =
        PaymentCard(
            CardNumber.create("1234123412341234").getOrThrow(),
            ExpireDate(YearMonth.now().plusMonths(1)),
            OwnerName("CREW"),
            Password("1234"),
        )
    val dummyPaymentCardList1 = listOf(dummyPaymentCard)

    AndroidpaymentsTheme {
        CardListScreen({}, dummyPaymentCardList1)
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenManyCardPreview() {
    val dummyPaymentCard =
        PaymentCard(
            CardNumber.create("1234123412341234").getOrThrow(),
            ExpireDate(YearMonth.now().plusMonths(1)),
            OwnerName("CREW"),
            Password("1234"),
        )

    val dummyPaymentCardList5 =
        List(5) { index ->
            dummyPaymentCard
        }
    AndroidpaymentsTheme {
        CardListScreen({}, dummyPaymentCardList5)
    }
}
