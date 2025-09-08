package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField
import woowacourse.payments.ui.transformation.GroupedVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = CardNumber.CARD_NUMBER_LENGTH,
        label = { Text(stringResource(R.string.card_number)) },
        placeholder = { Text("0000 - 0000 - 0000 - 0000") },
        isError = value.isNotEmpty() && runCatching { CardNumber.from(value) }.isFailure,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation =
            GroupedVisualTransformation(
                List(4) { CARD_NUMBER_GROUP_SIZE },
                " - ",
            ),
        inputFilter = { it.filter(Char::isDigit) },
        modifier = modifier,
    )
}

private const val CARD_NUMBER_GROUP_SIZE = 4
