package woowacourse.payments.ui.cardform.component

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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardholderNameUiModel

@Composable
fun CardholderNameTextField(
    cardholderName: CardholderNameUiModel,
    onCardholderNameChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardholderNameTextFieldDescription =
        stringResource(R.string.cardholder_name_text_field_description)
    OutlinedTextField(
        modifier =
            modifier.semantics { contentDescription = cardholderNameTextFieldDescription },
        label = {
            Text(text = stringResource(R.string.cardholder_name_text_field_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.cardholder_name_text_field_placeholder),
                color = Color.Gray,
            )
        },
        value = cardholderName.value,
        onValueChange = { newValue -> onCardholderNameChanged(newValue) },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.cardholder_name_text_field_display_length_status,
                        cardholderName.value.length,
                        cardholderName.maxNameLength,
                    ),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
    )
}

@Preview(showBackground = true, name = "이름 미작성")
@Composable
private fun PlaceholderPreview() {
    CardholderNameTextField(
        cardholderName = CardholderNameUiModel(),
        onCardholderNameChanged = { },
    )
}

@Preview(showBackground = true, name = "이름 작성")
@Composable
private fun WriteNamePreview() {
    var cardholderName by remember { mutableStateOf("CN") }
    CardholderNameTextField(
        cardholderName = CardholderNameUiModel(cardholderName),
        onCardholderNameChanged = { newValue -> cardholderName = newValue },
    )
}
