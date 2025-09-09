package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

private const val MAX_CARD_HOLDER_LENGTH = 30

@Composable
fun CardHolderTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val onlyLetters = input.filter { it.isLetterOrDigit() }
            val limited = onlyLetters.take(MAX_CARD_HOLDER_LENGTH)
            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(id = R.string.new_card_holder_name_label)) },
        placeholder = { Text(stringResource(id = R.string.new_card_holder_name_hint)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        supportingText = {
            Text(
                text = "${value.length}/$MAX_CARD_HOLDER_LENGTH",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardHolderTextFieldPreview() {
    CardHolderTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier,
    )
}
