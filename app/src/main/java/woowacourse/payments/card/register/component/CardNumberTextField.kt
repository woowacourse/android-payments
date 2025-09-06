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
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = cardNumber,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            cardNumber =
                if (stripped.length <= 16) {
                    stripped
                } else {
                    stripped.substring(0, 16)
                }
        },
        label = { Text(stringResource(R.string.register_card_number_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_number_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = cardNumberVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
private fun cardNumberVisualTransformation() =
    VisualTransformation { text ->
        var out = ""
        for (i in text.indices) {
            out += text[i]
            if (i % 4 == 3 && i != 15) out += "-"
        }

        TransformedText(
            text = AnnotatedString(out),
            offsetMapping =
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        if (offset <= 3) return offset
                        if (offset <= 7) return offset + 1
                        if (offset <= 11) return offset + 2
                        if (offset <= 16) return offset + 3
                        return 19
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        if (offset <= 4) return offset
                        if (offset <= 9) return offset - 1
                        if (offset <= 14) return offset - 2
                        if (offset <= 19) return offset - 3
                        return 16
                    }
                },
        )
    }
