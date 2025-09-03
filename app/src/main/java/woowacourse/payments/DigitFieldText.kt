package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun DigitFieldText(
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    fraction: Float = 1f,
    maxLength: Int = Int.MAX_VALUE,
    mask: InputMask = InputMask.None,
) {
    var number by remember { mutableStateOf("") }
    val visualTransformation = when(mask) {
        is InputMask.Password -> PasswordVisualTransformation()
        is InputMask.CardNumber -> {
            VisualTransformation { text ->
                creditCardFilter(text)
            }
        }
        is InputMask.Expiry -> {
            VisualTransformation { text ->
                expiryFilter(text)
            }
        }
        is InputMask.None -> VisualTransformation.None
    }
    OutlinedTextField(
        value = number,
        onValueChange = { newText ->
            val filtered = newText.filter { it.isDigit() }
            if (filtered.length <= maxLength) {
                number = filtered
            }

        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },
        modifier = modifier
            .fillMaxWidth(fraction)
            .padding(horizontal = 24.dp, vertical = 15.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation
    )
}

@Preview
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        DigitFieldText(
            label = "카드 번호",
            hint = "0000 - 0000 - 0000 - 0000",
            maxLength = 16,
            mask = InputMask.CardNumber,
        )
    }
}
