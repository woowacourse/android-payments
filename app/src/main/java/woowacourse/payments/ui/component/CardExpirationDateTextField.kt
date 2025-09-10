package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.ui.common.GroupedVisualTransformation
import woowacourse.payments.ui.model.CardExpirationDateUiModel

private const val CARD_EXPIRATION_DATE_GROUP_SIZE = 2
private const val CARD_EXPIRATION_DATE_SEPARATOR = " / "

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: CardExpirationDateUiModel,
    onCardExpirationDateChanged: (CardExpirationDateUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
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
        value = cardExpirationDate.cardExpirationDate,
        onValueChange = { newValue ->
            runCatching { CardExpirationDateUiModel(newValue) }
                .onSuccess { newExpirationDate -> onCardExpirationDateChanged(newExpirationDate) }
        },
        isError = cardExpirationDate.isExpired || cardExpirationDate.isInvalidDate,
        supportingText = {
            when {
                cardExpirationDate.isInvalidDate -> Text(text = stringResource(R.string.card_expiration_date_text_field_invalid_date))
                cardExpirationDate.isExpired -> Text(text = stringResource(R.string.card_expiration_date_text_field_expired))
            }
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardExpirationDateTextFieldPreview(
    @PreviewParameter(CardExpirationDateTextFieldPreviewParameterProvider::class) cardExpirationDate: CardExpirationDateUiModel,
) {
    CardExpirationDateTextField(
        cardExpirationDate = cardExpirationDate,
        onCardExpirationDateChanged = {},
    )
}

private class CardExpirationDateTextFieldPreviewParameterProvider : PreviewParameterProvider<CardExpirationDateUiModel> {
    override val values =
        sequenceOf(
            CardExpirationDateUiModel(""),
            CardExpirationDateUiModel("1226"),
            CardExpirationDateUiModel("1223"),
            CardExpirationDateUiModel("1326"),
        )
}
