package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.cardregister.CardNumberVisualTransformation

@Composable
fun CardNumberInputField(
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_place_holder),
                color = Color.Gray,
            )
        },
        visualTransformation = CardNumberVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberInputFieldPreview() {
    CardNumberInputField(
        text = "",
        onValueChange = { },
        isError = false,
    )
}
