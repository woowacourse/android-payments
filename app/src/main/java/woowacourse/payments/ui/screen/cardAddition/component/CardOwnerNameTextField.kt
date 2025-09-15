package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

private const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30

@Composable
fun CardOwnerNameTextField(
    value: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onKeyboardActionClick: (KeyboardActionScope) -> Unit = {},
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue: String ->
            val newName = newValue.take(CARD_OWNER_NAME_LENGTH_MAX)
            onNameChange(newName)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_owner_name_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.card_owner_name_placeholder), color = Color.Gray)
        },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.card_owner_name_supporting_text,
                        value.length,
                        CARD_OWNER_NAME_LENGTH_MAX,
                    ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onNext = onKeyboardActionClick),
        singleLine = true,
    )
}

@Preview
@Composable
private fun CardOwnerNameTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CardOwnerNameTextField(value = text, onNameChange = { text = it })
}
