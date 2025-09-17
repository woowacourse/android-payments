package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.R
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.newcard.CreateCardStateHolder.Companion.CARD_OWNER_NAME_MAX
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_EXPIRY_DATE_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_NUMBERS_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_OWNER_NAME_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_PASSWORD_INPUT_TAG
import woowacourse.payments.ui.newcard.model.NewCardUiState
import woowacourse.payments.ui.utils.GroupedSeparatorVisualTransformation

private val CARD_GROUPS = intArrayOf(4, 4, 4, 4)
private val EXPIRY_GROUPS = intArrayOf(2, 2)

private const val SEPARATOR_CARD = "-"
private const val SEPARATOR_EXPIRY = "/"
private val KEYBOARD_OPTIONS_NUMBER = KeyboardOptions(keyboardType = KeyboardType.Number)
private val KEYBOARD_OPTIONS_PIN = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardInputSection(
    newCardUiState: NewCardUiState,
    onCardNumbersChange: (String) -> Unit,
    onCardExpiryDateChange: (String) -> Unit,
    onCardOwnerNameChange: (String) -> Unit,
    onCardPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardNumbersVisualTransformation =
        remember {
            GroupedSeparatorVisualTransformation(CARD_GROUPS, SEPARATOR_CARD)
        }

    val expiryDateVisualTransformation =
        remember {
            GroupedSeparatorVisualTransformation(EXPIRY_GROUPS, SEPARATOR_EXPIRY)
        }
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = newCardUiState.cardNumber,
            onValueChange = onCardNumbersChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(CARD_NUMBERS_INPUT_TAG),
            label = { Text(stringResource(R.string.card_number_label)) },
            placeholder = { Text(stringResource(R.string.card_number_placeholder)) },
            visualTransformation = cardNumbersVisualTransformation,
            keyboardOptions = KEYBOARD_OPTIONS_NUMBER,
        )

        val expiryDateErrorTextRes =
            newCardUiState.expiryDateErrorTextRes?.let { stringResource(it) }

        OutlinedTextField(
            value = newCardUiState.expiryDate,
            onValueChange = onCardExpiryDateChange,
            modifier = Modifier
                .width(146.dp)
                .testTag(CARD_EXPIRY_DATE_INPUT_TAG),
            label = { Text(stringResource(R.string.expiry_label)) },
            isError = expiryDateErrorTextRes != null,
            placeholder = { Text(stringResource(R.string.expiry_placeholder)) },
            supportingText = {
                Text(
                    expiryDateErrorTextRes ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            },
            visualTransformation = expiryDateVisualTransformation,
            keyboardOptions = KEYBOARD_OPTIONS_NUMBER,
        )

        OutlinedTextField(
            value = newCardUiState.ownerName,
            onValueChange = onCardOwnerNameChange,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .testTag(CARD_OWNER_NAME_INPUT_TAG),
            label = { Text(stringResource(R.string.owner_label)) },
            placeholder = { Text(stringResource(R.string.owner_placeholder)) },
            supportingText = {
                Text(
                    "${newCardUiState.ownerName.length}/$CARD_OWNER_NAME_MAX",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            },
        )

        OutlinedTextField(
            value = newCardUiState.password,
            onValueChange = onCardPasswordChange,
            modifier =
                Modifier
                    .width(146.dp)
                    .testTag(CARD_PASSWORD_INPUT_TAG),
            label = { Text(stringResource(R.string.password_label)) },
            placeholder = { Text(stringResource(R.string.password_placeholder)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KEYBOARD_OPTIONS_PIN,
        )
    }
}

@Preview
@Composable
fun NewCardInputSectionPreview() {
    NewCardInputSection(
        NewCardUiState(
            cardNumber = "12345678",
            expiryDate = "13/22",
            ownerName = "동전감전장전공전구전사전기전상전무전중전체전고전신전생전수전",
            password = "1234",
            expiryDateErrorTextRes = R.string.validate_card_expiry_invalid_month,
        ), {}, {}, {}, {}
    )
}
