package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.R
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField

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
        label = { Text(stringResource(R.string.card_holder_name)) },
        placeholder = { Text(stringResource(R.string.input_card_holder_name)) },
        isError = value.isNotEmpty() && runCatching { CardHolderName(value) }.isFailure,
        supportingText = {
            Text(
                "${value.length}/${CardHolderName.MAX_NAME_LENGTH}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        inputFilter = {
            it.uppercase().filter { ch -> ch.isLetter() || ch.isWhitespace() }
        },
        modifier = modifier,
    )
}
