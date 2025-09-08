package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField
import woowacourse.payments.ui.transformation.GroupedVisualTransformation
import java.time.format.DateTimeFormatter

@Composable
fun CardExpirationDateTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LimitedLengthOutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 4,
        label = { Text(stringResource(R.string.card_expiration_date)) },
        placeholder = { Text("MM / YY") },
        isError =
            value.isNotEmpty() &&
                runCatching {
                    CardExpirationDate.from(value, DATE_TIME_FORMATTER)
                }.fold(
                    onSuccess = { it.isExpired() },
                    onFailure = { true },
                ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation =
            GroupedVisualTransformation(
                List(2) { EXPIRATION_DATE_GROUP_SIZE },
                " / ",
            ),
        inputFilter = { it.filter(Char::isDigit) },
        modifier = modifier,
    )
}

private const val EXPIRATION_DATE_GROUP_SIZE = 2
private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMyy")
