package woowacourse.payments.component

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
import woowacourse.payments.CardExpiryDateVisualTransformation
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
            if (it.length <= 4 && it.all { it.isDigit() })
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
