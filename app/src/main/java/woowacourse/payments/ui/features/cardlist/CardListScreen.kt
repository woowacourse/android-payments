package woowacourse.payments.ui.features.cardlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import woowacourse.payments.ui.components.PaymentCardPlate
import woowacourse.payments.ui.features.cardlist.components.AddPaymentCard
import woowacourse.payments.ui.features.cardlist.components.PaymentsTopBar
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Black700

@Composable
fun CardListScreen(
    onAddCard: () -> Unit,
    onEditCard: (PaymentCardUiModel) -> Unit = {},
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
        LazyColumn(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 카드가 없을 때만 안내 문구 표시
            if (cardUiModels.isEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(R.string.card_list_add_payment_card_title),
                        fontSize = 18.sp,
                        letterSpacing = 0.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black700,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            } else {
                // 카드가 1장 이상일 때 카드 목록 표시
                items(
                    items = cardUiModels,
                    key = { cardUiModel -> cardUiModel.dbId },
                ) { cardUiModel ->
                    Spacer(modifier = Modifier.height(12.dp))
                    PaymentCardPlate(
                        paymentCardUiModel = cardUiModel,
                        modifier = Modifier.clickable { onEditCard(cardUiModel) },
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }
            // 카드가 한장 이하일 때만 '카드 추가' 패널 표시
            if (cardUiModels.size <= 1) {
                item {
                    AddPaymentCard(modifier = Modifier.clickable { onAddCard() })
                    Spacer(modifier = Modifier.height(32.dp))
                }
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
            -1,
            CardCompanyUiModel.BC,
            "1234 - 1234 - 1234 - 1234",
            "02 / 26",
            "CREW",
        )
    val dummyPaymentCardList1 = listOf(dummyPaymentCardUiModel)

    AndroidpaymentsTheme {
        CardListScreen({}, {}, dummyPaymentCardList1)
    }
}

@Preview(showBackground = true)
@Composable
fun CardListScreenManyCardPreview() {
    AndroidpaymentsTheme {
        val dummyPaymentCardList5 =
            List(5) { index ->
                PaymentCardUiModel(
                    dbId = index,
                    cardCompanyUiModel = CardCompanyUiModel.BC,
                    formattedCardNumber = "1234 - 1234 - 1234 - 1234",
                    formattedExpireDate = "02 / 26",
                    ownerName = "CREW",
                )
            }
        CardListScreen({}, {}, dummyPaymentCardList5)
    }
}