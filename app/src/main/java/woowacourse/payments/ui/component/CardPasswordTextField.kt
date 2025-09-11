package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardPasswordUiModel

@Composable
fun CardPasswordTextField(
    cardPassword: CardPasswordUiModel,
    onCardPasswordChanged: (CardPasswordUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.semantics { contentDescription = "비밀번호" },
        label = {
            Text(text = stringResource(R.string.card_password_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_password_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardPassword.value,
        onValueChange = { newValue ->
            val newCardPassword: CardPasswordUiModel =
                runCatching { CardPasswordUiModel(newValue) }.getOrNull()
                    ?: return@OutlinedTextField
            if (!newCardPassword.isValid) return@OutlinedTextField
            onCardPasswordChanged(newCardPassword)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun CardPasswordTextFieldPreview() {
    var cardPassword by remember { mutableStateOf(CardPasswordUiModel("1234")) }

    Box(modifier = Modifier.padding(12.dp)) {
        CardPasswordTextField(
            cardPassword = cardPassword,
            onCardPasswordChanged = { newValue -> cardPassword = newValue },
        )
    }
}
