package woowacourse.payments.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.util.CardExpiryDateVisualTransformation
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Grey10

@Composable
fun CardExpiryDate(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= CARD_EXPIRY_DATE_MAX_LENGTH && it.all { it.isDigit() })
                onValueChange(it)
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.card_expiry_date)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_expire_date_placeholder),
                color = Grey10,
            )
        },
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

private const val CARD_EXPIRY_DATE_MAX_LENGTH = 4
