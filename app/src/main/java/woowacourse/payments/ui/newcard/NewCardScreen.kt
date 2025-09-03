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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.newcard.components.CounterTextField
import woowacourse.payments.ui.newcard.components.DigitsTextField
import woowacourse.payments.ui.newcard.components.PaymentCard

private const val CARD_NUMBER_MAX_LENGTH = 16
private const val EXPIRY_MAX_LENGTH = 4
private const val HOLDER_MAX_LENGTH = 30
private const val PIN_MAX_LENGTH = 4
private const val SEPARATOR_GROUP = " - "
private const val SEPARATOR_EXPIRY = " / "

@Composable
fun NewCardScreen(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onSaved: () -> Unit = {},
) {
    val focus = LocalFocusManager.current
    val scroll = rememberScrollState()

    var cardNumber by remember { mutableStateOf("") }
    var expiry by remember { mutableStateOf("") }
    var holder by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }

    Column(
        modifier =
            Modifier
                .padding(contentPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(scroll),
    ) {
        Spacer(Modifier.height(14.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            PaymentCard()
        }
        Spacer(Modifier.height(40.dp))

        DigitsTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = "카드 번호",
            placeholder = "0000 - 0000 - 0000 - 0000",
            maxLength = CARD_NUMBER_MAX_LENGTH,
            format = ::formatCardNumber,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            onImeAction = { focus.moveFocus(FocusDirection.Next) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(30.dp))

        DigitsTextField(
            value = expiry,
            onValueChange = { expiry = it },
            label = "만료일",
            placeholder = "MM / YY",
            maxLength = EXPIRY_MAX_LENGTH,
            format = ::formatExpiry,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next,
                ),
            onImeAction = { focus.moveFocus(FocusDirection.Next) },
            modifier = Modifier.width(160.dp),
        )
        Spacer(Modifier.height(30.dp))

        CounterTextField(
            value = holder,
            onValueChange = { holder = it },
            label = "카드 소유자 이름(선택)",
            placeholder = "카드에 표시된 이름을 입력하세요.",
            maxLength = HOLDER_MAX_LENGTH,
            showCounter = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onImeAction = { focus.moveFocus(FocusDirection.Next) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(10.dp))

        DigitsTextField(
            value = pin,
            onValueChange = { pin = it },
            label = "비밀번호",
            placeholder = "0000",
            maxLength = PIN_MAX_LENGTH,
            format = { it },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = ImeAction.Done,
                ),
            onImeAction = {
                focus.clearFocus()
                onSaved()
            },
            modifier = Modifier.width(160.dp),
        )
        Spacer(Modifier.height(24.dp))
    }
}

private fun formatCardNumber(rawDigits: String): String {
    if (rawDigits.isEmpty()) return ""
    val chunks = rawDigits.chunked(4).take(4)
    return chunks.joinToString(SEPARATOR_GROUP)
}

private fun formatExpiry(rawDigits: String): String {
    if (rawDigits.isEmpty()) return ""
    val mm = rawDigits.take(2)
    val yy = rawDigits.drop(2)
    return if (yy.isEmpty()) mm else "$mm$SEPARATOR_EXPIRY$yy"
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    AndroidpaymentsTheme {
        NewCardScreen(
            contentPadding = PaddingValues(0.dp),
            onSaved = {},
        )
    }
}
