package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.ui.screen.addCard.AddCardError
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardOwnerInputField(
    onOwnerChange: (CardOwner) -> Unit,
    modifier: Modifier = Modifier,
    cardOwner: CardOwner? = null,
    error: AddCardError? = null,
) {
    val context = LocalContext.current

    OutlinedTextField(
        value = cardOwner?.value.orEmpty(),
        onValueChange = { newText ->
            if (newText.length <= 20) {
                val newCardOwner = CardOwner(newText)
                onOwnerChange(newCardOwner)
            }
        },
        modifier =
            modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = context.getString(R.string.card_owner_content_description)
                },
        placeholder = {
            Text(
                text = stringResource(R.string.card_owner_placeholder),
                color = Color.LightGray,
            )
        },
        supportingText = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = if (error != null) stringResource(error.messageRes) else "",
                    modifier =
                        Modifier.semantics {
                            contentDescription =
                                context.getString(R.string.card_owner_error_content_description)
                        },
                    color = Color.Red,
                )
                Text(
                    text =
                        stringResource(
                            id = R.string.card_owner_length,
                            cardOwner?.value?.length ?: 0,
                        ),
                    color = Color.Gray,
                )
            }
        },
        label = { Text(text = stringResource(R.string.card_owner_label)) },
        isError = error != null,
    )
}

@Composable
@Preview(showBackground = true)
fun CardOwnerInputPreview() {
    AndroidpaymentsTheme {
        CardOwnerInputField(
            onOwnerChange = { },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardOwnerInputErrorPreview() {
    AndroidpaymentsTheme {
        CardOwnerInputField(
            onOwnerChange = { },
            error = AddCardError.OWNER_INVALID,
        )
    }
}
