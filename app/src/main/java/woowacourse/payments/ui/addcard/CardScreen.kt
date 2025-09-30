package woowacourse.payments.ui.addcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardScreenCategory
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Dimens
import woowacourse.payments.ui.theme.Dimens.AddCardComposableComponentPadding
import woowacourse.payments.ui.theme.Dimens.AddCardComposableScreenPadding

@Composable
fun CardScreen(
    card: Card,
    cardScreenCategory: CardScreenCategory,
    onCardChange: (Card) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    isCardSavable: Boolean,
    isSheetVisible: Boolean,
    onChangeSheetVisible: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    AndroidpaymentsTheme {
        Scaffold(
            topBar = {
                CardTopBar(
                    titleResId = cardScreenCategory.topBarTitleId,
                    onBackClick = onBackClick,
                    onSaveClick = { onSaveClick() },
                    isOnSaveClickable = isCardSavable,
                )
            },
            modifier = modifier.fillMaxSize(),
        ) { innerPadding ->
            if (isSheetVisible) {
                BankSelectBottomSheet(
                    onDismiss = { onChangeSheetVisible(false) },
                    onBankSelect = { bank ->
                        onCardChange(card.copy(bank = bank))
                        onChangeSheetVisible(false)
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
                            .clickable { onChangeSheetVisible(true) },
                    bank = card.bank.toUiModel(),
                )
                CardNumberField(
                    card.number.toUiModel(),
                    onValueChange = { input ->
                        onCardChange(card.copy(number = CardNumber.fromRawInput(input)))
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
                        onCardChange(
                            card.copy(
                                expirationDate = CardExpirationDate.fromRawInput(input),
                            ),
                        )
                    },
                    expirationDate = card.expirationDate.toUiModel(),
                    isValid = card.expirationDate.isValid(),
                )
                CardOwnerField(
                    cardOwner = card.ownerName.toUiModel(),
                    Modifier
                        .fillMaxWidth(),
                    onValueChange = { input ->
                        onCardChange(card.copy(ownerName = OwnerName.fromRawInput(input)))
                    },
                )
                PasswordField(
                    password = card.password.toUiModel(),
                    onValueChange = { input ->
                        onCardChange(card.copy(password = Password.fromRawInput(input)))
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
    CardScreen(
        onBackClick = {},
        onSaveClick = {},
        card = Card(),
        onCardChange = {},
        cardScreenCategory = CardScreenCategory.Add,
        isCardSavable = true,
        isSheetVisible = true,
        onChangeSheetVisible = {},
    )
}
