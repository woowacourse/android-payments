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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CardExpirationDateTextField(modifier: Modifier = Modifier) {
    var expirationDate by remember { mutableStateOf("") }
    val numericRegex = Regex("[^0-9]")

    OutlinedTextField(
        value = expirationDate,
        onValueChange = {
            val stripped = numericRegex.replace(it, "")
            expirationDate = if (stripped.length <= 4) {
                stripped
            } else {
                stripped.substring(0, 4)
            }
        },
        label = { Text("만료일") },
        placeholder = { Text("MM / YY") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        visualTransformation = VisualTransformation { text ->
            var out = ""
            for (i in text.indices) {
                out += text[i]
                if (i % 2 == 1 && i != 3) {
                    out += " / "
                }
            }

            val expirationDateOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    if (offset <= 2) return offset + 3
                    if (offset <= 4) return offset + 3
                    return 7
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    if (offset <= 5) return offset - 3
                    if (offset <= 7) return offset - 3
                    return 4
                }
            }

            TransformedText(
                text = AnnotatedString(out),
                offsetMapping = expirationDateOffsetTranslator
            )
        },
        modifier = modifier
    )
}
