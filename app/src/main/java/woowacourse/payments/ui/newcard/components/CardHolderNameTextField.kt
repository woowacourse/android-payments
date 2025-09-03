package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardHolderName

@Composable
fun CardHolderNameTextField(modifier: Modifier = Modifier) {
    var cardHolderName: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardHolderName,
        onValueChange = { text: String ->
            if (text.length <= CardHolderName.MAX_NAME_LENGTH) cardHolderName = text
        },
        label = { Text(stringResource(R.string.card_holder_name)) },
        placeholder = { Text(stringResource(R.string.input_card_holder_name)) },
        supportingText = {
            Text(
                "${cardHolderName.length}/${CardHolderName.MAX_NAME_LENGTH}",
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun CardHolderNameTextFieldPreview() {
    CardHolderNameTextField()
}
