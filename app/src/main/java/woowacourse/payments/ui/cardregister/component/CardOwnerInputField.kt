package woowacourse.payments.ui.cardregister.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun CardOwnerInputField(
    text: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.card_owner_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_owner_placeholder),
                color = Color.Gray,
            )
        },
        singleLine = true,
        supportingText = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(1f))
                Text(
                    stringResource(R.string.card_owner_length, text.length),
                )
            }
        },
        isError = isError,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardOwnerInputFieldPreview() {
    CardOwnerInputField(
        text = "",
        onValueChange = { },
        isError = false,
    )
}
