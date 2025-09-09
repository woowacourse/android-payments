package woowacourse.payments.card.register.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.R

@Composable
fun CardHolderNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            val stripped = input.filter { it.isLetter() }
            val uppercased = stripped.uppercase()
            val limited = uppercased.take(30)

            onValueChange(limited)
        },
        modifier = modifier,
        label = { Text(stringResource(R.string.register_card_holder_name_text_field_label)) },
        placeholder = { Text(stringResource(R.string.register_card_holder_name_text_field_placeholder)) },
        singleLine = true,
        supportingText = {
            Text(
                text = "${value.length} / 30",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}
