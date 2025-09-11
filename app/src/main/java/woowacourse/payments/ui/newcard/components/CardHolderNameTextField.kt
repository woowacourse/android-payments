package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.R
import woowacourse.payments.domain.CardHolderName

@Composable
fun CardHolderNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = CardHolderName.MAX_NAME_LENGTH,
        label = { Text(stringResource(R.string.card_holder_name_label)) },
        placeholder = { Text(stringResource(R.string.input_card_holder_name_placeholder)) },
        isError = value.isNotEmpty() && runCatching { CardHolderName(value) }.isFailure,
        supportingText = {
            Text(
                "${value.length}/${CardHolderName.MAX_NAME_LENGTH}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Next,
            ),
        inputFilter = {
            it.uppercase().filter { ch -> ch.isLetter() || ch.isWhitespace() }
        },
        modifier = modifier,
    )
}
