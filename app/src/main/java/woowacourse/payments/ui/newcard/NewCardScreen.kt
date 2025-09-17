package woowacourse.payments.ui.newcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.designsystem.theme.Black
import woowacourse.payments.designsystem.theme.GrayHint
import woowacourse.payments.designsystem.theme.GrayOutline
import woowacourse.payments.designsystem.theme.GrayText
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.components.BankSelectBottomSheet
import woowacourse.payments.ui.newcard.components.CardHolderTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.ExpiryTextField
import woowacourse.payments.ui.newcard.components.NewCardPreviewCard
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PinTextField
import woowacourse.payments.ui.newcard.model.rememberBoundScrollState
import woowacourse.payments.ui.newcard.model.rememberNewCardState

@Composable
fun NewCardScreen(
    onSaved: (CardUiModel) -> Unit = {},
    onFinish: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    val holder = rememberNewCardState()
    val scrollState = rememberBoundScrollState(holder)

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { onFinish() },
                onSaveClick = {
                    if (holder.canSave) {
                        onSaved(holder.createCardUiModel())
                    }
                },
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
                    if (holder.canSave) onSaved(holder.createCardUiModel())
                },
                colors = formTextFieldColors(),
            )
        }
    }
    BankSelectBottomSheet(
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
private fun NewCardScreenPreview() {
    AndroidpaymentsTheme {
        NewCardScreen()
    }
}
