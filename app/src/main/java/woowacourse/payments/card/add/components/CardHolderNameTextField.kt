package woowacourse.payments.card.add.components

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

@Composable
fun CardHolderNameTextField(modifier: Modifier = Modifier) {
    var cardHolderName: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardHolderName,
        onValueChange = { cardHolderName = it },
        label = { Text("카드 소유자 이름 (선택)") },
        placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
        supportingText = {
            Text(
                "0/30",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardHolderNameTextFieldPreview() {
    CardHolderNameTextField()
}
