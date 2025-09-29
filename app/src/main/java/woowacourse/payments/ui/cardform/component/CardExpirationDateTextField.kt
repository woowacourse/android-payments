package woowacourse.payments.ui.cardform.component

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
import woowacourse.payments.R
import woowacourse.payments.ui.common.ExpirationDateVisualTransformation
import woowacourse.payments.ui.model.CardExpirationDateUiModel

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: CardExpirationDateUiModel,
    onCardExpirationDateChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
) {
    val cardExpirationDateTextFieldDescription =
        stringResource(R.string.card_expiration_date_text_field_description)

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
        onValueChange = { newExpirationDate: String -> onCardExpirationDateChanged(newExpirationDate) },
        isError = errorMessage != null,
        supportingText = { errorMessage?.let { Text(text = it) } },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = ExpirationDateVisualTransformation,
    )
}

@Preview(showBackground = true, name = "유효한 만료일")
@Composable
private fun ValidCardExpirationDateTextFieldPreview() {
    CardExpirationDateTextField(
        cardExpirationDate = CardExpirationDateUiModel("1226"),
        onCardExpirationDateChanged = {},
    )
}

@Preview(showBackground = true, name = "유효하지 않은 만료일")
@Composable
private fun InValidCardExpirationDateTextFieldPreview() {
    CardExpirationDateTextField(
        cardExpirationDate = CardExpirationDateUiModel("0125"),
        onCardExpirationDateChanged = {},
        errorMessage = "유효하지 않은 만료일입니다",
    )
}
