package woowacourse.payments.ui.addcard

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Dimens
import woowacourse.payments.ui.theme.Dimens.AddCardComposableComponentPadding
import woowacourse.payments.ui.theme.Dimens.AddCardComposableScreenPadding

@Composable
fun CardCreationScreen(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    var card by remember { mutableStateOf(Card()) }
    var isSheetVisible by remember { mutableStateOf(true) }

    AndroidpaymentsTheme {
        Scaffold(
            topBar = {
                AddCardTopBar(
                    onBackClick = onBackClick,
                    onSaveClick = { onSaveClick(card) },
                    isOnSaveClickable = card.isValid(),
                )
            },
            modifier = modifier.fillMaxSize(),
        ) { innerPadding ->
            if (isSheetVisible) {
                BankSelectBottomSheet(
                    onDismiss = { isSheetVisible = false },
                    onBankSelected = { bank ->
                        card = card.copy(bank = bank)
                        isSheetVisible = false
                    },
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(AddCardComposableScreenPadding),
            ) {
                PaymentCard(
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { isSheetVisible = true },
                    bank = card.bank,
                )
                CardNumberField(
                    card.number,
                    onValueChange = { input ->
                        card = card.copy(number = CardNumber.fromRawInput(input))
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = AddCardComposableComponentPadding),
                )
                ExpirationDateField(
                    modifier =
                        Modifier
                            .fillMaxWidth(Dimens.FIELD_HALF_WIDTH),
                    onValueChange = { input ->
                        card =
                            card.copy(
                                expirationDate = CardExpirationDate.fromRawInput(input),
                            )
                    },
                    expirationDate = card.expirationDate,
                )
                CardOwnerField(
                    cardOwner = card.ownerName,
                    Modifier
                        .fillMaxWidth(),
                    onValueChange = { input ->
                        card = card.copy(ownerName = OwnerName.fromRawInput(input))
                    },
                )
                PasswordField(
                    password = card.password,
                    onValueChange = { input ->
                        card = card.copy(password = Password.fromRawInput(input))
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth(Dimens.FIELD_HALF_WIDTH),
                )
            }
        }
    }
}

enum class BankType(
    val bankName: String,
    @DrawableRes
    val bankLogo: Int,
    @ColorInt
    val color: Long,
) {
    NOT_SELECTED("", R.drawable.bc_card_img, 0xFF333333),
    BC("BC카드", R.drawable.bc_card_img, 0xFFF04651),
    SINHAN("신한카드", R.drawable.sinhan_card_img, 0xFF0046FF),
    KAKAO("카카오뱅크", R.drawable.kakao_card_img, 0xFFFFE300),
    HYUNDE("현대카드", R.drawable.hyunde_card_img, 0xFF000000),
    URI("우리카드", R.drawable.uri_card_img, 0xFF007BC8),
    LOTTE("롯데카드", R.drawable.lotte_card_img, 0xFFED1C24),
    HANA("하나카드", R.drawable.hana_card_img, 0xFF009490),
    KB("국민카드", R.drawable.kb_card_img, 0xFF554E45),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onDismiss: () -> Unit,
    onBankSelected: (BankType) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismiss,
    ) {
        BankSelectRow(onClick = onBankSelected)
    }
}

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    onClick: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(47.dp, 55.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        maxItemsInEachRow = COLUMN_COUNT,
        maxLines = ROW_COUNT,
        verticalArrangement = Arrangement.spacedBy(23.dp),
    ) {
        val bankList = BankType.entries.filter { it != BankType.NOT_SELECTED }
        bankList.forEach { bank ->
            BankSelectBtn(
                bank = bank,
                onClick = { onClick(bank) },
            )
        }
    }
}

@Composable
fun BankSelectBtn(
    bank: BankType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(69.dp)
                .height(65.dp)
                .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(37.dp),
            painter = painterResource(bank.bankLogo),
            contentDescription = "${bank.bankName} 로고",
        )
        Text(bank.bankName, fontSize = 16.sp, letterSpacing = (-0.085).em)
    }
}

@Preview(showBackground = true)
@Composable
private fun GenerateCardPreview() {
    CardCreationScreen(onBackClick = {}, onSaveClick = {})
}
