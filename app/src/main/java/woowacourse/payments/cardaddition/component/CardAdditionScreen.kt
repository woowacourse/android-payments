package woowacourse.payments.cardaddition.component

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.BankType
import woowacourse.payments.Card
import woowacourse.payments.EXTRA_CARD
import woowacourse.payments.cardaddition.CardAdditionStateHolder
import woowacourse.payments.cardaddition.CardAdditionUiState
import woowacourse.payments.ui.component.PaymentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAdditionScreen(
    modifier: Modifier = Modifier,
    stateHolder: CardAdditionStateHolder = rememberSaveable(saver = CardAdditionStateHolder.Saver) { CardAdditionStateHolder() },
) {
    val state: CardAdditionUiState = stateHolder.uiState
    val activity: Activity? = LocalActivity.current
    val scrollState = rememberScrollState()

    if (!state.isBankSelected) {
        BankSelectBottomSheet(stateHolder::updateBankType)
    }

    Scaffold(
        modifier = modifier.testTag("CardAdditionScreen"),
        topBar = {
            CardAdditionTopAppBar(
                completable = state.isValid,
                onBackClick = { activity?.finish() },
                onSaveClick = {
                    activity?.setResult(
                        RESULT_OK,
                        Intent().putExtra(
                            EXTRA_CARD,
                            Card(
                                number = state.cardNumber,
                                owner = state.holder,
                                expiredDate = state.expiredDate,
                                bankType = state.bankType,
                            ),
                        ),
                    )

                    activity?.finish()
                },
            )
        },
    ) { paddingValues: PaddingValues ->
        CardAdditionContent(
            state = state,
            onCardNumberChange = stateHolder::updateCardNumber,
            onExpiredDateChange = stateHolder::updateExpiredDate,
            onHolderChange = stateHolder::updateHolder,
            onPasswordChange = stateHolder::updatePassword,
            onSelectBank = stateHolder::updateBankType,
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
        )
    }
}

@Composable
private fun CardAdditionContent(
    state: CardAdditionUiState,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onHolderChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSelectBank: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        PaymentCard(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp, bottom = 28.dp)
                    .clickable(onClick = { onSelectBank(BankType.NOT_SELECTED) }),
            number = state.cardNumber,
            owner = state.holder,
            expiredDate = state.expiredDate,
            bankType = state.bankType,
        )
        CardNumberTextField(
            value = state.cardNumber,
            onValueChange = onCardNumberChange,
            isError = state.cardNumber.isNotBlank() && !state.isValidCardNumber,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
        )
        ExpiredDateTextField(
            value = state.expiredDate,
            onValueChange = onExpiredDateChange,
            isError = state.expiredDate.isNotBlank() && !state.isValidExpiredDate,
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
        CardOwnerNameTextField(
            value = state.holder,
            onValueChange = onHolderChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 18.dp),
            maxLength = state.holderMaxLength,
        )
        PasswordTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            isError = state.password.isNotBlank() && !state.isValidPassword,
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun CardAdditionScreenPreview(
    @PreviewParameter(CardAdditionScreenPreviewParameterProvider::class) state: CardAdditionUiState,
) {
    CardAdditionScreen(
        stateHolder = CardAdditionStateHolder(state),
    )
}

private class CardAdditionScreenPreviewParameterProvider : PreviewParameterProvider<CardAdditionUiState> {
    override val values: Sequence<CardAdditionUiState> =
        sequenceOf(
            CardAdditionUiState(
                bankType = BankType.BC,
                cardNumber = "",
                expiredDate = "",
                holder = "",
                password = "",
            ),
            CardAdditionUiState(
                bankType = BankType.BC,
                cardNumber = "1234",
                expiredDate = "125",
                holder = "",
                password = "12",
            ),
            CardAdditionUiState(
                bankType = BankType.BC,
                cardNumber = "1234".repeat(4),
                expiredDate = "1225",
                holder = "CREW",
                password = "1234",
            ),
        )
}
