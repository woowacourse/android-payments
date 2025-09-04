package woowacourse.payments.component

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
import woowacourse.payments.CardExpiryDateVisualTransformation

@Composable
fun CardExpiryDate(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 4 && it.all { it.isDigit() })
                onValueChange(it)
        },
        modifier = modifier,
        label = { Text("만료일") },
        placeholder = { Text("MM / YY") },
        visualTransformation = CardExpiryDateVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
fun CardExpiryDatePreview() {
    CardExpiryDate(
        value = "",
        onValueChange = {}
    )
}
