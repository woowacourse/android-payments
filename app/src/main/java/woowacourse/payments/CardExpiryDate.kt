package woowacourse.payments

import androidx.compose.foundation.layout.fillMaxWidth
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
fun CardExpiryDate() {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text("만료일") },
        placeholder = { Text("MM / YY") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.padding(top = 30.dp).padding(horizontal = 24.dp).fillMaxWidth(0.6f)
        )
}

@Composable
@Preview(showBackground = true)
fun CardExpiryDatePreview() {
    CardExpiryDate()
}
