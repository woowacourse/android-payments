package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.common.component.CardChip
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.CardAdditionUiStateHolder
import woowacourse.payments.ui.screen.cardAddition.toUiModel

@Composable
fun CardAdditionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveClick: (CardUiModel) -> Unit = {},
) {
    val stateHolder =
        rememberSaveable(saver = CardAdditionUiStateHolder.Saver) { CardAdditionUiStateHolder() }
    val isCompletable = remember { derivedStateOf { stateHolder.uiState.isValidCard } }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = onBackClick,
                onSaveClick = { onSaveClick(stateHolder.uiState.toUiModel()) },
                isCompletable = isCompletable.value,
            )
        },
    ) { paddingValues: PaddingValues ->
        if (!stateHolder.hasShownSheet) {
            BankSelectBottomSheet(
                onBankSelected = { issuingBank ->
                    stateHolder.updateIssuingBank(issuingBank)
                },
                onDismissRequest = { stateHolder.updateSheetVisible() },
            )
        }
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .semantics {
                        contentDescription =
                            context.getString(R.string.card_addition_fields_description)
                    },
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
                issuingBank = stateHolder.uiState.issuingBank,
                cardContent = { CardChip(modifier = Modifier.padding(start = 14.dp)) },
            )
            CardNumberTextField(
                value = stateHolder.uiState.cardNumber.value,
                onCardNumberChange = { newCardNumber: String ->
                    stateHolder.updateCardNumber(newCardNumber)
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .semantics {
                            contentDescription =
                                context.getString(R.string.card_addition_card_number_field_description)
                        },
                onComplete = { focusManager.moveFocus(FocusDirection.Next) },
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            ExpiredDateTextField(
                value = stateHolder.uiState.expiredDate.value,
                onDateChange = { newExpiredDate: String ->
                    stateHolder.updateExpiredDate(newExpiredDate)
                },
                modifier =
                    Modifier
                        .padding(top = 18.dp)
                        .semantics {
                            contentDescription =
                                context.getString(R.string.card_addition_card_expired_date_field_description)
                        },
                errorMessage =
                    if (stateHolder.uiState.isDateError) {
                        stringResource(
                            R.string.expired_date_error,
                        )
                    } else {
                        null
                    },
                onComplete = {
                    if (stateHolder.uiState.expiredDate.isValid) {
                        focusManager.moveFocus(
                            FocusDirection.Next,
                        )
                    }
                },
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            CardOwnerNameTextField(
                value = stateHolder.uiState.ownerName,
                onNameChange = { newOwnerName: String ->
                    stateHolder.updateCardOwnerName(newOwnerName)
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
                onKeyboardActionClick = { focusManager.moveFocus(FocusDirection.Next) },
            )
            PasswordTextField(
                value = stateHolder.uiState.password.value,
                onPasswordChange = { newPassword: String ->
                    stateHolder.updatePassword(newPassword)
                },
                modifier =
                    Modifier.semantics {
                        contentDescription =
                            context.getString(R.string.card_addition_card_password_field_description)
                    },
                onComplete = { focusManager.clearFocus() },
                onKeyboardActionClick = { focusManager.clearFocus() },
            )
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}
