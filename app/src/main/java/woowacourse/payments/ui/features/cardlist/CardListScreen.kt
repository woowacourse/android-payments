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
import woowacourse.payments.ui.components.PaymentCardPlate
import woowacourse.payments.ui.features.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.features.cardlist.components.PaymentsTopBar
import woowacourse.payments.ui.model.CardCompany
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Black700

@Composable
fun CardListScreen(
    onAddCard: () -> Unit,
    cardUiModels: List<PaymentCardUiModel> = emptyList(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            PaymentsTopBar(
                onAddClick = onAddCard,
                isAddButtonVisible = cardUiModels.size > 1,
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
            if (cardUiModels.isEmpty()) {
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
                for (card in cardUiModels) {
                    PaymentCardPlate(paymentCardUiModel = card)
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }

            // 카드가 한장 이하일 때만 '카드 추가' 패널 표시
            if (cardUiModels.size <= 1) {
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
    val dummyPaymentCardUiModel =
        PaymentCardUiModel(
            CardCompany.BC,
            "1234 - 1234 - 1234 - 1234",
            "02 / 26",
            "CREW",
        )
    val dummyPaymentCardList1 = listOf(dummyPaymentCardUiModel)

    AndroidpaymentsTheme {
        CardListScreen({}, dummyPaymentCardList1)
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenManyCardPreview() {
    val dummyPaymentCardUiModel =
        PaymentCardUiModel(
            CardCompany.BC,
            "1234 - 1234 - 1234 - 1234",
            "02 / 26",
            "CREW",
        )
    val dummyPaymentCardList5 =
        List(5) { index ->
            dummyPaymentCardUiModel
        }
    AndroidpaymentsTheme {
        CardListScreen({}, dummyPaymentCardList5)
    }
}
