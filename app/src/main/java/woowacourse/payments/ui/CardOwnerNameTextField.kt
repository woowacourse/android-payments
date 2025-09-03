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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardOwnerNameTextField(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    OutlinedTextField(
        value = name,
        onValueChange = { newName: String ->
            name = newName.substring(0, newName.length.coerceAtMost(30))

        },
        modifier = modifier,
        label = {
            Text(text = "카드 소유자 이름(선택)")
        },
        placeholder = {
            Text(text = "카드에 표시된 이름을 입력하세요.", color = Color.Gray)
        },
        supportingText = {
            Text(
                text = "${name.length}/30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
    )
}

@Preview
@Composable
private fun CardOwnerNameTextFieldPreview() {
    CardOwnerNameTextField()
}