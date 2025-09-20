package woowacourse.payments.ui.cardRegister

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardCompany
import woowacourse.payments.ui.cardList.components.CardSelectionModal
import woowacourse.payments.ui.cardRegister.components.CardRegisterTopBar
import woowacourse.payments.ui.cardRegister.components.PaymentCard
import woowacourse.payments.ui.cardRegister.components.PaymentTextField
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.common.DateVisualTransformation
import woowacourse.payments.ui.common.model.CardCompanyUiType
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.common.model.toUiType
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.RedFFFF0000

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRegisterScreen(
    onBackClick: () -> Unit,
    onSaveClick: (card: CardUiModel) -> Unit,
    isNotValidInput: () -> Unit,
    cardRegisterState: CardRegisterState = rememberCardRegisterState(),
) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    Scaffold(
        topBar = {
            CardRegisterTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = {
                    if (cardRegisterState.isValid()) {
                        onSaveClick(cardRegisterState.cardUiModel)
                    } else {
                        isNotValidInput()
                    }
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        if (cardRegisterState.isShowingBottomSheet) {
            CardSelectionModal(
                modalBottomSheetState = modalBottomSheetState,
                onDismissRequest = { },
                onCardCompanyClick = { cardCompanyUiType: CardCompanyUiType ->
                    cardRegisterState.updateCardCompany(cardCompanyUiType)
                    scope
                        .launch {
                            modalBottomSheetState.hide()
                        }.invokeOnCompletion {
                            cardRegisterState.hideBottomSheet()
                        }
                },
                cardCompanies = CardCompany.entries.drop(1).map { it.toUiType() },
            )
        }
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .padding(top = 14.dp)
                        .align(Alignment.CenterHorizontally),
                card =
                    CardUiModel(
                        cardCompany = cardRegisterState.cardCompany,
                    ),
            )
            PaymentTextField(
                text = cardRegisterState.cardNumber,
                onValueChanged = { cardRegisterState.updateCardNumber(it) },
                label = stringResource(R.string.card_number_label),
                supportingText =
                    {
                        if (cardRegisterState.isShowingCardNumberError()) {
                            Text(
                                text = stringResource(R.string.card_number_supporting_text),
                                color = RedFFFF0000,
                            )
                        }
                    },
                placeholder = stringResource(R.string.card_number_place_holder),
                maxLength = 16,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = CreditCardVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
            )
            PaymentTextField(
                text = cardRegisterState.expiredDate,
                onValueChanged = { cardRegisterState.updateExpiredDate(it) },
                label = stringResource(R.string.expired_date_label),
                supportingText =
                    {
                        if (cardRegisterState.isShowingExpiredDateError()) {
                            Text(
                                text = stringResource(R.string.card_expired_date_supporting_text),
                                color = RedFFFF0000,
                            )
                        }
                    },
                placeholder = stringResource(R.string.expired_date_place_holder),
                maxLength = 4,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = DateVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 30.dp),
            )
            PaymentTextField(
                text = cardRegisterState.ownerName,
                onValueChanged = { cardRegisterState.updateOwnerName(it) },
                label = stringResource(R.string.card_owner_label),
                placeholder = stringResource(R.string.card_owner_place_holder),
                supportingText = {
                    Text(
                        text =
                            stringResource(
                                R.string.card_owner_supporting_text,
                                cardRegisterState.ownerName.length,
                            ),
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                maxLength = 30,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
            )
            PaymentTextField(
                text = cardRegisterState.password,
                onValueChanged = { cardRegisterState.updatePassword(it) },
                label = stringResource(R.string.card_password_label),
                supportingText =
                    {
                        if (cardRegisterState.isShowingOwnerNameError()) {
                            Text(
                                text = stringResource(R.string.card_password_supporting_text),
                                color = RedFFFF0000,
                            )
                        }
                    },
                placeholder = stringResource(R.string.card_password_place_holder),
                maxLength = 4,
                onlyDigits = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PasswordVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 10.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardRegisterScreenPreview() {
    AndroidpaymentsTheme {
        CardRegisterScreen(
            {},
            {},
            {},
            cardRegisterState = rememberCardRegisterState(isShowingBottomSheet = false),
        )
    }
}
