package woowacourse.payments.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.util.CardNumberVisualTransformation
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Grey10

@Composable
fun CardNumber(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= 16 && it.all { it.isDigit() }) onValueChange(it) },
        modifier = modifier,
        label = { Text(stringResource(R.string.card_number)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Grey10,
            )
        },
        singleLine = true,
        visualTransformation = CardNumberVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

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
