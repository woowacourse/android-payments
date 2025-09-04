package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardOwnerNameTextField(modifier: Modifier = Modifier) {
    var name: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = name,
        onValueChange = { newName: String ->
            name = newName.take(CARD_OWNER_NAME_LENGTH_MAX).uppercase()
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_owner_name_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_owner_name_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.card_owner_name_supporting_text,
                        name.length,
                        CARD_OWNER_NAME_LENGTH_MAX,
                    ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Ascii,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview
@Composable
private fun CardOwnerNameTextFieldPreview() {
    CardOwnerNameTextField()
}

private const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30
