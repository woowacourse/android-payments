package woowacourse.payments.ui

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter { it.isDigit() }
            expiredDate = newDate.substring(0, newDate.length.coerceAtMost(4))
        },
        modifier = modifier,
        label = { Text(text = "만료일") },
        placeholder = { Text(text = "MM/YY", color = Color.Gray) },
        visualTransformation = { text ->
            val trimmed = text.substring(0, text.length.coerceAtMost(4))
            var out = ""
            trimmed.forEachIndexed { index, ch ->
                out += ch
                if (index == 1) out += " / "
            }
            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    return offset + 3
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    return offset - 3
                }

            }
            TransformedText(AnnotatedString(out), offsetMapping)
        }
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}