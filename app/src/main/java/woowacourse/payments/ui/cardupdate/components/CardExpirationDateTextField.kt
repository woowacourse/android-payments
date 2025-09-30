package woowacourse.payments.ui.cardupdate.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.ui.transformation.GroupedVisualTransformation

@Composable
fun CardExpirationDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 4,
        label = { Text(stringResource(R.string.card_expiration_date_label)) },
        placeholder = { Text("MM / YY") },
        isError =
            value.isNotEmpty() && !isValid,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Next,
            ),
        visualTransformation =
            remember {
                GroupedVisualTransformation(EXPIRATION_DATE_GROUPS, " / ")
            },
        inputFilter = { it.filter(Char::isDigit) },
        modifier = modifier,
    )
}

private val EXPIRATION_DATE_GROUPS = listOf(2, 2)
