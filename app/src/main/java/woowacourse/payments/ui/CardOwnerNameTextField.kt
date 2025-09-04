package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
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
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_owner_name_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.card_owner_name_placeholder), color = Color.Gray)
        },
        supportingText = {
            Text(
                text = stringResource(
                    R.string.card_owner_name_supporting_text,
                    value.length,
                    maxLength,
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}

@Preview
@Composable
private fun CardOwnerNameTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    CardOwnerNameTextField(value = text, onValueChange = { text = it }, maxLength = 30)
}
