package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.transformation.GroupedVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = CardNumber.CARD_NUMBER_LENGTH,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = { Text("0000 - 0000 - 0000 - 0000") },
        isError = value.isNotEmpty() && !isValid,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
        visualTransformation =
            remember {
                GroupedVisualTransformation(CARD_NUMBER_GROUPS, " - ")
            },
        inputFilter = { it.filter(Char::isDigit) },
        modifier = modifier,
    )
}

private val CARD_NUMBER_GROUPS = listOf(4, 4, 4, 4)
