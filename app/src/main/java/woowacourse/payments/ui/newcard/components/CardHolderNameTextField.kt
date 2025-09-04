package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardHolderNameTextField(
    cardHolderName: String,
    maxLength: Int,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = cardHolderName,
        onValueChange = { text: String ->
            if (text.length <= maxLength) {
                onValueChange(text)
            }
        },
        label = { Text(stringResource(R.string.card_holder_name)) },
        placeholder = { Text(stringResource(R.string.input_card_holder_name)) },
        supportingText = {
            Text(
                "${cardHolderName.length}/$maxLength",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardHolderNameTextFieldPreview() {
    CardHolderNameTextField(
        cardHolderName = "토바에",
        maxLength = 30,
        onValueChange = {},
        modifier = Modifier,
    )
}
