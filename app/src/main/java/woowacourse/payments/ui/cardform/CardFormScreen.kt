package woowacourse.payments.ui.cardform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.designsystem.theme.Black
import woowacourse.payments.designsystem.theme.GrayHint
import woowacourse.payments.designsystem.theme.GrayOutline
import woowacourse.payments.designsystem.theme.GrayText
import woowacourse.payments.ui.cardform.components.BankSelector
import woowacourse.payments.ui.cardform.components.CardFormTopBar
import woowacourse.payments.ui.cardform.components.CardHolderTextField
import woowacourse.payments.ui.cardform.components.CardNumberTextField
import woowacourse.payments.ui.cardform.components.ExpiryTextField
import woowacourse.payments.ui.cardform.components.NewCardPreviewCard
import woowacourse.payments.ui.cardform.components.PinTextField
import woowacourse.payments.ui.cardform.model.ActionType
import woowacourse.payments.ui.cardform.model.rememberCardFormState
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun CardFormScreen(
    actionType: ActionType,
    initialCard: CardUiModel? = null,
    saveCard: (CardUiModel) -> Unit,
    navigateToBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val holder = rememberCardFormState()

    LaunchedEffect(actionType, initialCard) {
        if (actionType == ActionType.EDIT && initialCard != null) {
            holder.updateCardNumber(initialCard.numberDigits)
            holder.updateExpiry(initialCard.expiry)
            holder.updateHolder(initialCard.holder)
            holder.updateBank(initialCard.bankType)
        }
    }

    fun saveCard() {
        if (holder.canSave) {
            saveCard(holder.createCardUiModel())
        }
    }

    Scaffold(
        topBar = {
            CardFormTopBar(
                onBackClick = { navigateToBack() },
                onSaveClick = { saveCard() },
                title =
                    stringResource(
                        when (actionType) {
                            ActionType.NEW -> R.string.new_card
                            ActionType.EDIT -> R.string.edit_card
                        },
                    ),
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
        ) {
            Spacer(Modifier.height(14.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                NewCardPreviewCard(
                    bankType = holder.selectedBank,
                    modifier = Modifier.clickable { holder.updateBankSheet(true) },
                )
            }

            Spacer(Modifier.height(28.dp))

            CardNumberTextField(
                value = holder.cardNumber,
                onValueChange = { holder.updateCardNumber(it) },
                modifier = Modifier.fillMaxWidth(),
                onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
                colors = formTextFieldColors(),
            )

            Spacer(Modifier.height(18.dp))

            ExpiryTextField(
                value = holder.expiry,
                onValueChange = { holder.updateExpiry(it) },
                modifier = Modifier.width(146.dp),
                onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
                colors = formTextFieldColors(),
            )

            Spacer(Modifier.height(18.dp))

            CardHolderTextField(
                value = holder.holder,
                onValueChange = { holder.updateHolder(it) },
                modifier = Modifier.fillMaxWidth(),
                onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
                colors = formTextFieldColors(),
            )

            PinTextField(
                value = holder.pin,
                onValueChange = { holder.updatePin(it) },
                modifier = Modifier.width(146.dp),
                onImeAction = {
                    focusManager.clearFocus()
                    saveCard()
                },
                colors = formTextFieldColors(),
            )
        }
    }

    BankSelector(
        isOpen = holder.isBankSheetOpen,
        selected = holder.selectedBank,
        onSelected = { holder.updateBank(it) },
        onDismiss = { holder.updateBankSheet(false) },
    )
}

@Composable
private fun formTextFieldColors(): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        focusedBorderColor = GrayOutline,
        unfocusedBorderColor = GrayOutline,
        disabledBorderColor = GrayOutline,
        errorBorderColor = GrayOutline,
        focusedLabelColor = GrayText,
        unfocusedLabelColor = GrayText,
        focusedPlaceholderColor = GrayHint,
        unfocusedPlaceholderColor = GrayHint,
        focusedTextColor = Black,
        unfocusedTextColor = Black,
        cursorColor = Black,
    )

@Preview(showBackground = true)
@Composable
private fun CardFormScreenPreview() {
    AndroidpaymentsTheme {
        CardFormScreen(
            actionType = ActionType.NEW,
            initialCard = null,
            saveCard = {},
            navigateToBack = {},
        )
    }
}
