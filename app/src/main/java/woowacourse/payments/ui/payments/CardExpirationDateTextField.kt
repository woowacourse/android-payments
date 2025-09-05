package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import woowacourse.payments.R
import woowacourse.payments.ui.common.GroupedVisualTransformation
import java.time.YearMonth

private const val CARD_EXPIRATION_DATE_LENGTH = 4
private const val CENTURY_PREFIX = "20"
private const val CARD_EXPIRATION_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRATION_DATE_SEPARATOR = " / "

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: String,
    onCardExpirationDateChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    var isValidYearMonth by rememberSaveable { mutableStateOf(true) }
    val visualTransformation =
        remember {
            GroupedVisualTransformation(
                CARD_EXPIRATION_DATE_GROUP_SIZE,
                CARD_EXPIRATION_DATE_SEPARATOR,
            )
        }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_expiration_date_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_expiration_date_text_field_placeholder)) },
        value = cardExpirationDate,
        onValueChange = { newValue ->
            if (newValue.length > CARD_EXPIRATION_DATE_LENGTH) return@OutlinedTextField
            if (newValue.isDigitsOnly().not()) return@OutlinedTextField

            isValidYearMonth = isValidYearMonth(newValue)
            onCardExpirationDateChanged(newValue)
        },
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let { message -> return@OutlinedTextField Text(text = message) }
            if (isValidYearMonth.not()) Text(text = stringResource(R.string.card_expiration_date_text_field_invalid_format))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
            ),
        modifier = modifier,
    )
}

private fun isValidYearMonth(cardExpirationDate: String): Boolean {
    if (cardExpirationDate.length != CARD_EXPIRATION_DATE_LENGTH) return true

    val month = cardExpirationDate.take(2).toInt()
    val year = (CENTURY_PREFIX + cardExpirationDate.takeLast(2)).toInt()
    return runCatching { YearMonth.of(year, month) }.isSuccess
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardExpirationDateTextFieldPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        CardExpirationDateTextField(
            cardExpirationDate = "",
            onCardExpirationDateChanged = {},
        )

        CardExpirationDateTextField(
            cardExpirationDate = "1226",
            onCardExpirationDateChanged = {},
        )

        CardExpirationDateTextField(
            cardExpirationDate = "1326",
            onCardExpirationDateChanged = {},
            errorMessage = "유효하지 않은 만료일 입니다.",
        )
    }
}
