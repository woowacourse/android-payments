package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.ui.screen.addCard.AddCardError
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardOwnerInputField(
    modifier: Modifier = Modifier,
    cardOwner: CardOwner? = null,
    onOwnerChange: (CardOwner) -> Unit,
    error: AddCardError? = null,
) {
    Column {
        OutlinedTextField(
            value = cardOwner?.value.orEmpty(),
            onValueChange = { newText ->
                if (newText.length <= 20) {
                    val newCardOwner = CardOwner(newText)
                    onOwnerChange(newCardOwner)
                }
            },
            modifier =
                modifier.semantics {
                    this.contentDescription = "Card Owner Input Field"
                },
            placeholder = {
                Text(
                    text = stringResource(R.string.card_owner_placeholder),
                    color = Color.LightGray,
                )
            },
            supportingText = {
                Text(
                    text =
                        stringResource(
                            id = R.string.card_owner_length,
                            cardOwner?.value?.length ?: 0,
                        ),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                )
            },
            label = { Text(text = stringResource(R.string.card_owner_label)) },
            isError = error != null,
        )

        error?.let {
            Text(
                text = stringResource(R.string.card_owner_invalid),
                modifier =
                    Modifier.semantics {
                        this.contentDescription = "Card Owner Input Error"
                    },
                color = Color.Red,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardOwnerInputPreview() {
    AndroidpaymentsTheme {
        CardOwnerInputField(
            cardOwner = CardOwner(""),
            onOwnerChange = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardOwnerInputErrorPreview() {
    AndroidpaymentsTheme {
        CardOwnerInputField(
            cardOwner = CardOwner(""),
            onOwnerChange = { },
            error = AddCardError.OWNER_INVALID,
        )
    }
}
