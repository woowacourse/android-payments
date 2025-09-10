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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardPasswordUiModel

@Composable
fun CardPasswordTextField(
    cardPassword: String,
    onCardPasswordChanged: (String) -> Unit,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = rememberDefaultCardExpirationDateVisualTransformation(),
) {
    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_password_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_password_text_field_placeholder)) },
        value = cardPassword,
        onValueChange = onCardPasswordChanged,
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
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
private fun rememberDefaultCardExpirationDateVisualTransformation(): VisualTransformation = remember { PasswordVisualTransformation() }

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPasswordTextFieldPreview(
    @PreviewParameter(CardPasswordTextFieldPreviewParameterProvider::class) case:
        CardPasswordTextFieldPreviewParameterProvider.CardPasswordPreviewCase,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Preview Case: ${case.caseName}")
        HorizontalDivider()
        CardPasswordTextField(
            cardPassword = case.cardPassword,
            onCardPasswordChanged = {},
            errorMessage = case.errorMessage,
            modifier = Modifier.padding(12.dp),
        )
    }
}

private class CardPasswordTextFieldPreviewParameterProvider :
    PreviewParameterProvider<CardPasswordTextFieldPreviewParameterProvider.CardPasswordPreviewCase> {
    data class CardPasswordPreviewCase(
        val caseName: String,
        val cardPassword: String,
        val errorMessage: String?,
    )

    override val values =
        sequenceOf(
            CardPasswordPreviewCase("빈 값", "", null),
            CardPasswordPreviewCase("정상", "1234", null),
            CardPasswordPreviewCase("오류", "ABCD", "유효하지 않은 형식입니다."),
        )
}
