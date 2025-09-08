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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.R

@Composable
fun CardPasswordTextField(
    cardPassword: CardPasswordUiModel,
    onCardPasswordChanged: (CardPasswordUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val visualTransformation = remember { PasswordVisualTransformation() }

    OutlinedTextField(
        label = { Text(text = stringResource(R.string.card_password_text_field_label)) },
        placeholder = { Text(text = stringResource(R.string.card_password_text_field_placeholder)) },
        value = cardPassword.cardPassword,
        onValueChange = { newValue ->
            runCatching { CardPasswordUiModel(newValue) }
                .onSuccess(onCardPasswordChanged)
        },
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPasswordTextFieldPreview(
    @PreviewParameter(CardPasswordTextFieldPreviewParameterProvider::class) cardPassword: CardPasswordUiModel,
) {
    CardPasswordTextField(
        cardPassword = cardPassword,
        onCardPasswordChanged = {},
    )
}

private class CardPasswordTextFieldPreviewParameterProvider : PreviewParameterProvider<CardPasswordUiModel> {
    override val values =
        sequenceOf(
            CardPasswordUiModel(""),
            CardPasswordUiModel("1234"),
        )
}
