package woowacourse.payments.ui.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

private const val CardholderNameTextFieldTestTag = "CardholderNameTextField"
private const val CardholderNameLengthSeparator = "/"

@Composable
fun CardholderNameTextField(
    modifier: Modifier = Modifier,
    cardholderName: String,
    onCardholderNameChanged: (String) -> Unit,
    maxLength: Int = 30,
) {
    OutlinedTextField(
        modifier = modifier.testTag(CardholderNameTextFieldTestTag),
        label = {
            Text(text = stringResource(R.string.cardholder_name_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.cardholder_name_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardholderName,
        onValueChange = { newValue ->
            if (newValue.length > maxLength) {
                return@OutlinedTextField onCardholderNameChanged(
                    newValue.take(maxLength),
                )
            }
            if (newValue.any { it !in 'a'..'z' && it !in 'A'..'Z' }) return@OutlinedTextField
            onCardholderNameChanged(newValue.uppercase())
        },
        supportingText = {
            Text(
                text = "${cardholderName.length}$CardholderNameLengthSeparator$maxLength",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardholderNameTextFieldPreview() {
    var cardholderName by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(12.dp)) {
        CardholderNameTextField(
            cardholderName = cardholderName,
            onCardholderNameChanged = { newValue -> cardholderName = newValue },
        )
    }
}
