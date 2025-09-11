package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardOwnerNameTextField(
    ownerName: String,
    onOwnerNameChange: (String) -> Unit,
    ownerNameErrorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = ownerName,
        onValueChange = { newName: String ->
            onOwnerNameChange(newName.take(newName.length.coerceAtMost(CARD_OWNER_NAME_LENGTH_MAX)))
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_owner_name_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.card_owner_name_placeholder), color = Color.Gray)
        },
        isError = ownerNameErrorMessage != null,
        supportingText = {
            Text(
                text = stringResource(
                    R.string.card_owner_name_supporting_text,
                    ownerName.length,
                    CARD_OWNER_NAME_LENGTH_MAX
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
            if (ownerNameErrorMessage != null) {
                Text(
                    text = ownerNameErrorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
    )
}

@Preview
@Composable
private fun CardOwnerNameTextFieldPreview() {
    var ownerName by remember { mutableStateOf("") }
    CardOwnerNameTextField(
        ownerName = ownerName,
        onOwnerNameChange = { ownerName = it },
    )
}

private const val CARD_OWNER_NAME_LENGTH_MAX: Int = 30