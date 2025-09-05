package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        label = { Text(text = label) },
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) onValueChange(newValue)
        },
        placeholder = { Text(placeholder) },
        supportingText = {
            Text(
                text = stringResource(R.string.new_card_name_length, value.length, maxLength),
                textAlign = TextAlign.End,
                modifier =
                    Modifier
                        .fillMaxWidth(),
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun NameTextFieldPreview() {
    NameTextField(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 30.dp, end = 24.dp),
        value = "hwannow",
        onValueChange = {},
        label = stringResource(R.string.new_card_name_label),
        placeholder = stringResource(R.string.new_card_name_placeholder),
        maxLength = 30,
    )
}
