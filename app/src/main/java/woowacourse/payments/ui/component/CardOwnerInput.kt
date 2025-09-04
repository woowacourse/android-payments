package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
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
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardOwnerInput(
    cardOwner: CardOwner?,
    onOwnerChange: (CardOwner?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                if (newText.length <= 30) {
                    text = newText
                    onOwnerChange(CardOwner(newText))
                }
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.card_owner_placeholder),
                    color = Color.LightGray,
                )
            },
            supportingText = {
                Text(
                    text = stringResource(id = R.string.card_owner_length, text.length),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                )
            },
            label = { Text(text = stringResource(R.string.card_owner_label)) },
            isError = showValidationError && (cardOwner?.isValid != true),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardOwnerInputPreview() {
    AndroidpaymentsTheme {
        CardOwnerInput(
            cardOwner = null,
            onOwnerChange = { },
        )
    }
}
