package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun LimitedTextField(
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                text = newText
            }
        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 30.dp),
        supportingText = {
            Text(
                text = "${text.length}/${maxLength}",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        }
    )
}

@Preview
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        LimitedTextField(
            label = "카드 소유자 이름(선택)",
            hint = "카드에 표시된 이름을 입력하세요.",
            maxLength = 30,
        )
    }
}