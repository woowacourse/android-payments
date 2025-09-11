package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.extension.semanticsContentDescription
import woowacourse.payments.ui.model.CardholderNameUiModel

private const val INPUT_TEXT_COUNT_SEPARATOR = "%d/%d"

@Composable
fun CardholderNameTextField(
    cardholderName: String,
    onCardholderNameChanged: (String) -> Unit,
    maxLength: Int,
    errorMessage: String?,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        label = { Text(text = stringResource(R.string.cardholder_name_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.cardholder_name_text_field_placeholder)) },
        value = cardholderName,
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) onCardholderNameChanged(newValue)
        },
        isError = errorMessage != null,
        trailingIcon = {
            errorMessage?.let {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                )
            }
        },
        supportingText = {
            errorMessage?.let { message -> Text(text = message) }
            Text(
                text = INPUT_TEXT_COUNT_SEPARATOR.format(cardholderName.length, maxLength),
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
        modifier = modifier.semanticsContentDescription(R.string.cardholder_name_text_field_content_description),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardholderNameTextFieldPreview(
    @PreviewParameter(CardholderNameTextFieldPreviewParameterProvider::class) case:
        CardholderNameTextFieldPreviewParameterProvider.CardholderNamePreviewCase,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Preview Case: ${case.caseName}")
        HorizontalDivider()
        CardholderNameTextField(
            cardholderName = case.cardholderName,
            onCardholderNameChanged = {},
            maxLength = 30,
            errorMessage = case.errorMessage,
            modifier = Modifier.padding(horizontal = 12.dp),
        )
    }
}

private class CardholderNameTextFieldPreviewParameterProvider :
    PreviewParameterProvider<CardholderNameTextFieldPreviewParameterProvider.CardholderNamePreviewCase> {
    data class CardholderNamePreviewCase(
        val caseName: String,
        val cardholderName: String,
        val errorMessage: String?,
    )

    override val values =
        sequenceOf(
            CardholderNamePreviewCase("빈 값", "", null),
            CardholderNamePreviewCase("정상", "DICE", null),
            CardholderNamePreviewCase("오류", "1234", "유효하지 않은 형식입니다."),
        )
}
