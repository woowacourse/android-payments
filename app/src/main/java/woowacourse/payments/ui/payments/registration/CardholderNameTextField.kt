package woowacourse.payments.ui.payments.registration

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardholderNameUiModel

@Composable
fun CardholderNameTextField(
    cardholderName: CardholderNameUiModel,
    onCardholderNameChanged: (CardholderNameUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier =
            modifier.semantics { contentDescription = "카드 소유자 이름" },
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
        onValueChange = { newValue ->
            val newCardholderName: CardholderNameUiModel =
                runCatching { CardholderNameUiModel(newValue.uppercase()) }.getOrNull()
                    ?: return@OutlinedTextField
            if (!newCardholderName.isValid) return@OutlinedTextField
            onCardholderNameChanged(newCardholderName)
        },
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardholderNameTextFieldPreview() {
    var cardholderName by remember { mutableStateOf(CardholderNameUiModel()) }
    Column(modifier = Modifier.padding(12.dp)) {
        CardholderNameTextField(
            cardholderName = cardholderName,
            onCardholderNameChanged = { newValue -> cardholderName = newValue },
        )
    }
}
