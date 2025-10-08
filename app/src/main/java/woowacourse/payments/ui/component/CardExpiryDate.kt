package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.transformation.CardExpiryDateVisualTransformation

@Composable
fun CardExpiryDate(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { it ->
            if (it.length <= CARD_EXPIRY_DATE_MAX_LENGTH && it.all { it.isDigit() }) {
                onValueChange(it)
            }
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.card_expiry_date)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_expire_date_placeholder),
            )
        },
        visualTransformation = CardExpiryDateVisualTransformation(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}

@Composable
@Preview(showBackground = true)
private fun CardExpiryDatePreview() {
    CardExpiryDate(
        value = "0723",
        onValueChange = {},
    )
}

private const val CARD_EXPIRY_DATE_MAX_LENGTH = 4
