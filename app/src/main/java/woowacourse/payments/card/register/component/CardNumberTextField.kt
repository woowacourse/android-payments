package woowacourse.payments.card.register.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.R

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val stripped = numericRegex.replace(input, "")
            val limited = stripped.take(16)
            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.register_card_number_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_number_text_field_placeholder)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = cardNumberVisualTransformation(),
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
