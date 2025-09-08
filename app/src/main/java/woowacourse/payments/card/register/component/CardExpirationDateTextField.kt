package woowacourse.payments.card.register.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Preview
@Composable
fun CardExpirationDateTextField(modifier: Modifier = Modifier) {
    var expirationDate by remember { mutableStateOf("") }
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = expirationDate,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            expirationDate =
                if (stripped.length <= 4) {
                    stripped
                } else {
                    stripped.take(4)
                }
        },
        label = { Text(stringResource(R.string.register_card_expiration_date_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_expiration_date_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = expirationDateVisualTransformation(),
        modifier = modifier,
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
