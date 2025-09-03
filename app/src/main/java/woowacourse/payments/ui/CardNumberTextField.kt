package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newValue: String ->
            cardNumber = newValue.filter { it.isDigit() }
        },
        modifier = modifier.semantics {
            contentType = ContentType.CreditCardNumber
        },
        label = {
            Text(text = "카드 번호")
        },
        placeholder = {
            Text(
                text = "0000-0000-0000-0000",
                color = Color.Gray,
            )
        },
        visualTransformation = ::creditCardFilter
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(modifier = Modifier.fillMaxWidth())
}

private fun creditCardFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += "-"
    }

    val creditCardOffsetTranslator =
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
        }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}
