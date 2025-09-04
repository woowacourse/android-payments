package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardHolderName() {
    val cardHolderName: MutableState<String> = remember { mutableStateOf("") }

    OutlinedTextField(
        value = "cardHolderName.value",
        onValueChange = { if (it.length <= 30) cardHolderName.value = it },
        label = { Text("카드 소유자 이름(선택)") },
        placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        supportingText = {
            Text(
                "0/30",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = Modifier
            .padding(top = 30.dp)
            .padding(horizontal = 24.dp),
    )

}

@Composable
@Preview(showBackground = true)
fun CardHolderNamePreview() {
    CardHolderName()
}
