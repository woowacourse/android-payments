package woowacourse.payments.card.register.component

import androidx.compose.foundation.layout.fillMaxWidth
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

@Preview
@Composable
fun CardHolderNameTextField(modifier: Modifier = Modifier) {
    var cardHolderName by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardHolderName,
        onValueChange = { input ->
            val stripped = input.filter { it.isLetter() }
            val uppercased = stripped.uppercase()

            if (uppercased.length <= 30) {
                cardHolderName = uppercased
            }
        },
        label = { Text("카드 소유자 이름(선택)") },
        placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
        singleLine = true,
        modifier = modifier,
        supportingText = {
            Text(
                text = "${cardHolderName.length} / 30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}
