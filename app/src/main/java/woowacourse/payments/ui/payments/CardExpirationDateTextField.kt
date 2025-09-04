package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R
import woowacourse.payments.ui.common.ExpirationDateVisualTransformation
import java.time.YearMonth

private const val CARD_EXPIRATION_DATE_LENGTH = 4
private const val CENTURY_PREFIX = "20"
private const val CARD_EXPIRATION_DATE_TEXT_FIELD_TEST_TAG = "CardExpirationDateTextField"

@Composable
fun CardExpirationDateTextField(
    modifier: Modifier = Modifier,
    cardExpirationDate: String,
    onCardExpirationDateChanged: (String) -> Unit,
    errorMessage: String? = null,
    onErrorMessageChanged: (String?) -> Unit,
) {
    val isError = errorMessage != null

    if (cardExpirationDate.length == CARD_EXPIRATION_DATE_LENGTH) {
        if (isValidYearMonth(cardExpirationDate)) {
            onErrorMessageChanged(
                stringResource(R.string.card_expiration_date_text_field_invalid_format),
            )
        }
    }

    OutlinedTextField(
        modifier = modifier.testTag(CARD_EXPIRATION_DATE_TEXT_FIELD_TEST_TAG),
        label = {
            Text(text = stringResource(R.string.card_expiration_date_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_expiration_date_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardExpirationDate,
        onValueChange = { newValue ->
            if (newValue.length > CARD_EXPIRATION_DATE_LENGTH) {
                return@OutlinedTextField onCardExpirationDateChanged(
                    newValue.take(CARD_EXPIRATION_DATE_LENGTH),
                )
            }
            if (newValue.isDigitsOnly().not()) return@OutlinedTextField
            onErrorMessageChanged(null)
            onCardExpirationDateChanged(newValue)
        },
        isError = isError,
        supportingText = { if (isError) Text(text = errorMessage.orEmpty()) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ExpirationDateVisualTransformation,
    )
}

private fun isValidYearMonth(cardExpirationDate: String): Boolean {
    val month = cardExpirationDate.take(2).toInt()
    val year = (CENTURY_PREFIX + cardExpirationDate.takeLast(2)).toInt()
    return runCatching { YearMonth.of(year, month) }.isFailure
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardExpirationDateTextFieldPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        CardExpirationDateTextField(
            cardExpirationDate = "1226",
            onCardExpirationDateChanged = {},
            onErrorMessageChanged = {},
        )

        CardExpirationDateTextField(
            cardExpirationDate = "1326",
            onCardExpirationDateChanged = {},
            errorMessage = "유효하지 않은 만료일 입니다.",
            onErrorMessageChanged = {},
        )
    }
}
