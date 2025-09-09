package woowacourse.payments.card.register.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.R

@Composable
fun CardExpirationDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = value,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            val limited = stripped.take(4)

            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.register_card_expiration_date_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_expiration_date_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = expirationDateVisualTransformation(),
    )
}

@Composable
private fun expirationDateVisualTransformation() =
    VisualTransformation { text ->
        var out = ""
        for (i in text.indices) {
            out += text[i]
            if (i % 2 == 1 && i != 3) {
                out += " / "
            }
        }

        val expirationDateOffsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 2) return offset + 3
                    if (offset <= 4) return offset + 3
                    return 7
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    if (offset <= 4) return 2
                    if (offset <= 6) return offset - 3
                    return 4
                }
            }

        TransformedText(
            text = AnnotatedString(out),
            offsetMapping = expirationDateOffsetTranslator,
        )
    }
