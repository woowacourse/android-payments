package woowacourse.payments.ui.component.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.common.ExpirationDateVisualTransformation
import woowacourse.payments.ui.model.CardExpirationDateUiModel

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: CardExpirationDateUiModel,
    onCardExpirationDateChanged: (CardExpirationDateUiModel) -> Unit,
    onErrorMessageChanged: (String?) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    val cardExpirationDateTextFieldDescription =
        stringResource(R.string.card_expiration_date_text_field_description)

    val isError = errorMessage != null
    if (cardExpirationDate.isError) onErrorMessageChanged(stringResource(R.string.card_expiration_date_text_field_invalid_format))

    OutlinedTextField(
        modifier =
            modifier.semantics { contentDescription = cardExpirationDateTextFieldDescription },
        label = {
            Text(text = stringResource(R.string.card_expiration_date_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_expiration_date_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardExpirationDate.value,
        onValueChange = { newValue ->
            val newExpirationDate: CardExpirationDateUiModel =
                runCatching { CardExpirationDateUiModel(newValue) }.getOrNull()
                    ?: return@OutlinedTextField
            if (!newExpirationDate.isValid) return@OutlinedTextField
            onErrorMessageChanged(null)
            onCardExpirationDateChanged(newExpirationDate)
        },
        isError = isError,
        supportingText = { if (isError) Text(text = errorMessage.orEmpty()) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ExpirationDateVisualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardExpirationDateTextFieldPreview() {
    Column(modifier = Modifier.padding(12.dp)) {
        CardExpirationDateTextField(
            cardExpirationDate = CardExpirationDateUiModel("1226"),
            onCardExpirationDateChanged = {},
            onErrorMessageChanged = {},
        )

        CardExpirationDateTextField(
            cardExpirationDate = CardExpirationDateUiModel("1226"),
            onCardExpirationDateChanged = {},
            errorMessage = "유효하지 않은 만료일 입니다.",
            onErrorMessageChanged = {},
        )
    }
}
