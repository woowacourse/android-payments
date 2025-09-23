package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.OwnerName.Companion.CARD_OWNER_MAX_LENGTH
import woowacourse.payments.ui.model.OwnerNameUiModel

@Composable
fun CardOwnerField(
    cardOwner: OwnerNameUiModel,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = cardOwner.toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.add_card_card_owner_placeholder_text)) },
        label = { Text(stringResource(R.string.add_card_card_owner_label_text)) },
        singleLine = true,
        supportingText = {
            Text(
                stringResource(
                    R.string.add_card_card_owner_supporting_text,
                    cardOwner.toString().length,
                    CARD_OWNER_MAX_LENGTH,
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardOwnerFieldPreview() {
    CardOwnerField(
        cardOwner = OwnerName.fromRawInput("").toUiModel(),
    )
}
