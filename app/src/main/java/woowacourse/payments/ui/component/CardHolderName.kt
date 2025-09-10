package woowacourse.payments.ui.component

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
import woowacourse.payments.ui.theme.Grey10

@Composable
fun CardHolderName(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = { if (it.length <= CARD_HOLDER_NAME_MAX_LENGTH) onValueChange(it) },
        modifier = modifier,
        label = { Text(stringResource(R.string.card_holder_name)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_holder_name_placeholder),
                color = Grey10,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        supportingText = {
            Text(
                "${value.length}/$CARD_HOLDER_NAME_MAX_LENGTH",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        },
    )

}

@Composable
@Preview(showBackground = true)
private fun CardHolderNamePreview() {
    CardHolderName(
        value = "이든존예",
        onValueChange = {}
    )
}

private const val CARD_HOLDER_NAME_MAX_LENGTH = 30
