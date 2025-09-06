package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.addcard.util.CardNumberTransformation
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation

@Composable
fun CardNumberTextField(
    cardInfo: CardInfoUiState,
    modifier: Modifier = Modifier,
    ) {
    OutlinedTextField(
        modifier = modifier,
        value = cardInfo.cardNumber,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            cardInfo.updateCardInfo(cardNumber = it)
        },
        singleLine = true,
        label = { Text(stringResource(R.string.addcard_card_number_label)) },
        visualTransformation = if (cardInfo.cardNumber.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_card_number_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else CardNumberTransformation(),
    )
}