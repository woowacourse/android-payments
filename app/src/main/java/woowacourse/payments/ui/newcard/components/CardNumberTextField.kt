package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.newcard.util.transformation.CardNumberVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        value = value,
        label = { Text(text = stringResource(R.string.new_card_card_number_label)) },
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) onValueChange(newValue)
        },
        placeholder = { Text(stringResource(R.string.new_card_card_number_placeholder)) },
        visualTransformation = CardNumberVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 24.dp, end = 24.dp),
        value = "1234123412341234",
        onValueChange = {},
        maxLength = 16,
    )
}
