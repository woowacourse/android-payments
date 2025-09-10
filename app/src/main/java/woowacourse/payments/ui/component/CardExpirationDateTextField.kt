package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import woowacourse.payments.ui.model.CardExpirationDateUiModel

@Composable
fun CardExpirationDateTextField(
    cardExpirationDate: String,
    onCardExpirationDateChanged: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = rememberDefaultCardExpirationDateVisualTransformation(),
) {
    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_expiration_date_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_expiration_date_text_field_placeholder)) },
        value = cardExpirationDate,
        onValueChange = onCardExpirationDateChanged,
        isError = errorMessage != null,
        supportingText = { errorMessage?.let { message -> Text(text = message) } },
        trailingIcon = {
            errorMessage?.let {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                )
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

@Composable
private fun rememberDefaultCardExpirationDateVisualTransformation(): VisualTransformation =
    remember {
        GroupedVisualTransformation(
            groupSize = 2,
            separator = " / ",
        )
    }

@Preview(showBackground = true)
@Composable
private fun CardExpirationDateTextFieldPreview(
    @PreviewParameter(CardExpirationDatePreviewParameterProvider::class) case:
        CardExpirationDatePreviewParameterProvider.CardExpirationDatePreviewCase,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Preview Case: ${case.caseName}")
        HorizontalDivider()
        CardExpirationDateTextField(
            cardExpirationDate = case.cardExpirationDate,
            onCardExpirationDateChanged = {},
            errorMessage = case.errorMessage,
            modifier = Modifier.padding(horizontal = 12.dp),
        )
    }
}

private class CardExpirationDatePreviewParameterProvider :
    PreviewParameterProvider<CardExpirationDatePreviewParameterProvider.CardExpirationDatePreviewCase> {
    data class CardExpirationDatePreviewCase(
        val caseName: String,
        val cardExpirationDate: String,
        val errorMessage: String?,
    )

    override val values: Sequence<CardExpirationDatePreviewCase> =
        sequenceOf(
            CardExpirationDatePreviewCase("빈 값", "", null),
            CardExpirationDatePreviewCase("정상", "1233", null),
            CardExpirationDatePreviewCase("오류", "1333", "만료일 형식이 올바르지 않습니다."),
        )
}
