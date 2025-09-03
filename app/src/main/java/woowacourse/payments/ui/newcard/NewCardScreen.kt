package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.newcard.components.CounterTextField
import woowacourse.payments.ui.newcard.components.DigitsTextField
import woowacourse.payments.ui.newcard.components.PaymentCard

private const val CARD_NUMBER_MAX_LENGTH = 16
private const val EXPIRY_MAX_LENGTH = 4
private const val HOLDER_MAX_LENGTH = 30
private const val PIN_MAX_LENGTH = 4

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val EXPIRY_GROUP_SIZE = 2

private const val SEPARATOR_GROUP = " - "
private const val SEPARATOR_EXPIRY = " / "

@Composable
fun NewCardScreen(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onSaved: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var cardholder by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .padding(contentPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(scrollState),
    ) {
        Spacer(Modifier.height(14.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            PaymentCard()
        }
        Spacer(Modifier.height(40.dp))

        DigitsTextField(
            value = cardNumber,
            label = stringResource(R.string.new_card_number_label),
            placeholder = stringResource(R.string.new_card_number_hint),
            maxLength = CARD_NUMBER_MAX_LENGTH,
            grouping = IntArray(4) { CARD_NUMBER_GROUP_SIZE },
            separator = SEPARATOR_GROUP,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            onValueChange = { cardNumber = it },
            onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(30.dp))

        DigitsTextField(
            value = expiry,
            label = stringResource(R.string.new_card_expiry_label),
            placeholder = stringResource(R.string.new_card_expiry_hint),
            maxLength = EXPIRY_MAX_LENGTH,
            grouping = IntArray(2) { EXPIRY_GROUP_SIZE },
            separator = SEPARATOR_EXPIRY,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            onValueChange = { expiry = it },
            onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
            modifier = Modifier.width(160.dp),
        )
        Spacer(Modifier.height(30.dp))

        CounterTextField(
            value = cardholder,
            label = stringResource(R.string.new_card_holder_name_label),
            placeholder = stringResource(R.string.new_card_holder_name_hint),
            maxLength = HOLDER_MAX_LENGTH,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = { cardholder = it },
            onImeAction = { focusManager.moveFocus(FocusDirection.Next) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(10.dp))

        DigitsTextField(
            value = pin,
            label = stringResource(R.string.new_card_pin_label),
            placeholder = stringResource(R.string.new_card_pin_hint),
            maxLength = PIN_MAX_LENGTH,
            separator = "",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done,
                ),
            onValueChange = { pin = it },
            onImeAction = {
                focusManager.clearFocus()
                onSaved()
            },
            modifier = Modifier.width(160.dp),
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    AndroidpaymentsTheme {
        NewCardScreen()
    }
}
