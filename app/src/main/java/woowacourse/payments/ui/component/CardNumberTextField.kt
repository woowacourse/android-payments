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
import woowacourse.payments.ui.model.CardNumberUiModel

private const val CARD_NUMBER_GROUP_SIZE = 4
private const val CARD_NUMBER_SEPARATOR = " - "

@Composable
fun CardNumberTextField(
    cardNumber: CardNumberUiModel,
    onCardNumberChanged: (CardNumberUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visualTransformation =
        remember { GroupedVisualTransformation(CARD_NUMBER_GROUP_SIZE, CARD_NUMBER_SEPARATOR) }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_number_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_number_text_field_placeholder)) },
        value = cardNumber.cardNumber,
        onValueChange = { newValue ->
            runCatching { CardNumberUiModel(newValue) }
                .onSuccess { newCardNumber -> onCardNumberChanged(newCardNumber) }
        },
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
private fun CardNumberTextFieldPreview(
    @PreviewParameter(CardNumberTextFieldPreviewParameterProvider::class) cardNumber: CardNumberUiModel,
) {
    CardNumberTextField(
        cardNumber = cardNumber,
        onCardNumberChanged = {},
    )
}

private class CardNumberTextFieldPreviewParameterProvider : PreviewParameterProvider<CardNumberUiModel> {
    override val values =
        sequenceOf(
            CardNumberUiModel(""),
            CardNumberUiModel("1234123412341234"),
        )
}
