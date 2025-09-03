package woowacourse.payments.card.add.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardExpirationDateTextField(modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text("만료일") },
        placeholder = { Text("MM/YY") },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardExpirationDateTextFieldPreview() {
    CardExpirationDateTextField()
}
