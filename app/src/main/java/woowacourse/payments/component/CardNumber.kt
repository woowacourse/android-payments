package woowacourse.payments.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardNumber(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= 16) onValueChange(it) },
        label = { Text("카드 번호") },
        placeholder = { Text("0000 - 0000 - 0000 - 0000") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .padding(top = 40.dp)
            .padding(horizontal = 24.dp)
    )
}

@Preview
@Composable
fun CardNumberPreview() {
    CardNumber(
        value = "",
        onValueChange = {}
    )
}
