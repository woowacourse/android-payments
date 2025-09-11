package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.common.GroupedVisualTransformation
import woowacourse.payments.ui.extension.semanticsContentDescription

@Composable
fun CardNumberTextField(
    cardNumber: String,
    onCardNumberChanged: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = rememberDefaultCardExpirationDateVisualTransformation(),
) {
    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_number_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_number_text_field_placeholder)) },
        value = cardNumber,
        onValueChange = onCardNumberChanged,
        isError = errorMessage != null,
        trailingIcon = {
            errorMessage?.let {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                )
            }
        },
        supportingText = { errorMessage?.let { message -> Text(text = message) } },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
            ),
        modifier = modifier.semanticsContentDescription(R.string.card_number_text_field_content_description),
    )
}

@Composable
private fun rememberDefaultCardExpirationDateVisualTransformation(): VisualTransformation =
    remember {
        GroupedVisualTransformation(groupSize = 4, separator = " - ")
    }

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardNumberTextFieldPreview(
    @PreviewParameter(CardNumberTextFieldPreviewParameterProvider::class) case:
        CardNumberTextFieldPreviewParameterProvider.CardNumberTextFieldPreviewCase,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Preview Case: ${case.caseName}")
        HorizontalDivider()
        CardNumberTextField(
            cardNumber = case.cardNumber,
            onCardNumberChanged = {},
            errorMessage = case.errorMessage,
            modifier = Modifier.padding(12.dp),
        )
    }
}

private class CardNumberTextFieldPreviewParameterProvider :
    PreviewParameterProvider<CardNumberTextFieldPreviewParameterProvider.CardNumberTextFieldPreviewCase> {
    data class CardNumberTextFieldPreviewCase(
        val caseName: String,
        val cardNumber: String,
        val errorMessage: String?,
    )

    override val values =
        sequenceOf(
            CardNumberTextFieldPreviewCase("빈 값", "", null),
            CardNumberTextFieldPreviewCase("정상", "1234123412341234", null),
            CardNumberTextFieldPreviewCase("오류", "ABC", "유효하지 않은 형식입니다."),
        )
}
