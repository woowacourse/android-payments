package woowacourse.payments.ui.addcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showBackground = true)
@Composable
private fun GenerateCardPreview() {
    CardCreationScreen(onBackClick = {}, onSaveClick = {})
}
