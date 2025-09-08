package woowacourse.payments.ui.addcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation

@Composable
fun OwnerNameTextField(
    cardInfo: CardInfoUiState,
    modifier: Modifier = Modifier,
    ) {
    OutlinedTextField(
        modifier = modifier,
        value = cardInfo.ownerName,
        onValueChange = {
            cardInfo.updateCardInfo(ownerName = it)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.addcard_owner_name_label)) },
        supportingText = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = "${cardInfo.ownerName.length}/${CardInfoUiState.OWNER_NAME_MAX_SIZE}"
            )
        },
        visualTransformation = if (cardInfo.ownerName.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_owner_name_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else VisualTransformation.None
    )
}
