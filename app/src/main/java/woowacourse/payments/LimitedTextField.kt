package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun LimitedTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    imeAction: ImeAction = ImeAction.Done,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            onValueChange(newText.take(maxLength))
        },
        label = { Text(text = label) },
        placeholder = { Text(text = hint) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        supportingText = {
            Text(
                text = "${text.length}/$maxLength",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },

    )
}

@Preview
@Composable
private fun LimitedTextFieldPreview() {
    AndroidpaymentsTheme {
        LimitedTextField(
            text = "",
            onValueChange = {},
            label = "카드 소유자 이름(선택)",
            hint = "카드에 표시된 이름을 입력하세요.",
            maxLength = 30,
        )
    }
}
