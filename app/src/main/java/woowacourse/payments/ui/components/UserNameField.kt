package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.ui.theme.Grey40

@Composable
fun UserNameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            if (input.length <= MAX_LENGTH) onValueChange(input)
        },
        label = { Text("카드 소유자 이름(선택)") },
        placeholder = { Text("카드에 표시된 이름을 입력하세요.", color = Grey40) },
        supportingText = {
            Text(
                text = "${value.length} / $MAX_LENGTH",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        singleLine = true,
    )
}

private const val MAX_LENGTH = 30
