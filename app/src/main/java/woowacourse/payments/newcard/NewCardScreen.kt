package woowacourse.payments.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.cards.CardParcelable
import woowacourse.payments.cards.toParcelable
import woowacourse.payments.domain.Card
import woowacourse.payments.newcard.component.CardNumberTextField
import woowacourse.payments.newcard.component.ExpiredDateTextField
import woowacourse.payments.newcard.component.NewCardTopBar
import woowacourse.payments.newcard.component.OwnerNameTextField
import woowacourse.payments.newcard.component.PasswordTextField
import woowacourse.payments.util.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewCardScreen(
    newCardStateHolder: NewCardStateHolder = remember { NewCardStateHolder() },
    sheetState: SheetState =
        rememberModalBottomSheetState(confirmValueChange = { false }),
    onBackClick: () -> Unit = {},
    onSaveClick: (CardParcelable) -> Unit = {},
    onCardSaveFailed: () -> Unit = {},
) {
    if (!newCardStateHolder.isCardSelected) {
        ModalBottomSheet(
            onDismissRequest = {},
            sheetState = sheetState,
            properties = ModalBottomSheetProperties(shouldDismissOnBackPress = false),
        ) {
            CardCompanySelectionRow(
                onItemClick = { cardCompany ->
                    newCardStateHolder.selectedCardCompany = cardCompany
                },
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    val cardResult: Result<Card> = newCardStateHolder.getCard()

                    cardResult
                        .onSuccess {
                            onSaveClick(cardResult.getOrThrow().toParcelable())
                        }.onFailure {
                            onCardSaveFailed()
                        }
                },
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            PaymentCard(
                modifier = Modifier.padding(top = 14.dp),
                cardCompanyUiModel = CardCompanyUiModel.from(newCardStateHolder.selectedCardCompany),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 24.dp, horizontal = 40.dp),
            ) {
                CardNumberTextField(
                    value = newCardStateHolder.newCardUiState.value.cardNumber,
                    onValueChange = newCardStateHolder::updateCardNumber,
                    modifier = Modifier.fillMaxWidth(),
                )
                ExpiredDateTextField(
                    value = newCardStateHolder.newCardUiState.value.expiredDate,
                    onValueChange = newCardStateHolder::updateExpiredDate,
                )
                OwnerNameTextField(
                    value = newCardStateHolder.newCardUiState.value.ownerName,
                    onValueChange = newCardStateHolder::updateOwnerName,
                    modifier = Modifier.fillMaxWidth(),
                )
                PasswordTextField(
                    value = newCardStateHolder.newCardUiState.value.password,
                    onValueChange = newCardStateHolder::updatePassword,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CardCompanyModalBottomSheet() {
    val sheetState = rememberStandardBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { },
        sheetState = sheetState,
    ) {
        CardCompanySelectionRow()
    }
}
