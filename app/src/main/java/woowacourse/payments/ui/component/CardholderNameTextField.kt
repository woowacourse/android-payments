package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardholderNameUiModel

private const val INPUT_TEXT_COUNT_SEPARATOR = "%d/%d"

@Composable
fun CardholderNameTextField(
    cardholderName: CardholderNameUiModel,
    onCardholderNameChanged: (CardholderNameUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        label = { Text(text = stringResource(R.string.cardholder_name_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.cardholder_name_text_field_placeholder)) },
        value = cardholderName.cardholderName,
        onValueChange = { newValue ->
            runCatching { CardholderNameUiModel(newValue.uppercase()) }
                .onSuccess(onCardholderNameChanged)
        },
        supportingText = {
            Text(
                text =
                    INPUT_TEXT_COUNT_SEPARATOR.format(
                        cardholderName.length,
                        cardholderName.maxLength,
                    ),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
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
private fun CardholderNameTextFieldPreview(
    @PreviewParameter(CardholderNameTextFieldPreviewParameterProvider::class) cardholderName: CardholderNameUiModel,
) {
    CardholderNameTextField(
        cardholderName = cardholderName,
        onCardholderNameChanged = {},
    )
}

private class CardholderNameTextFieldPreviewParameterProvider : PreviewParameterProvider<CardholderNameUiModel> {
    override val values =
        sequenceOf(
            CardholderNameUiModel(""),
            CardholderNameUiModel("DICE"),
        )
}
