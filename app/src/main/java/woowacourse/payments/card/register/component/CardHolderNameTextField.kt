package woowacourse.payments.card.register.component

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

@Preview
@Composable
fun CardHolderNameTextField(modifier: Modifier = Modifier) {
    var cardHolderName by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardHolderName,
        onValueChange = { input ->
            val stripped = input.filter { it.isLetter() }
            val uppercased = stripped.uppercase()

            cardHolderName =
                if (uppercased.length <= 30) {
                    uppercased
                } else {
                    uppercased.take(30)
                }
        },
        label = { Text(stringResource(R.string.register_card_holder_name_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_holder_name_text_field_placeholder)) },
        singleLine = true,
        modifier = modifier,
        supportingText = {
            Text(
                text = "${cardHolderName.length} / 30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}
