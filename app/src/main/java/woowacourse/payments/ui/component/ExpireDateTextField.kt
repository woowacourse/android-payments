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
import woowacourse.payments.ui.addcard.util.ExpirationDateTransformation
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation

@Composable
fun ExpireDateTextField(
    modifier: Modifier = Modifier,
    cardInfo: CardInfoUiState,
) {
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        value = cardInfo.expireDate,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            cardInfo.updateCardInfo(expireDate = it)
        },
        isError = !cardInfo.isExpirationDateValid,
        label = { Text(stringResource(R.string.addcard_expire_date_label)) },
        supportingText = {
            if (!cardInfo.isExpirationDateValid) {
                Text("유효하지 않은 날짜입니다")
            }
        },
        visualTransformation = if (cardInfo.expireDate.isEmpty()) PlaceholderTransformation(
            placeholder = stringResource(R.string.addcard_expire_date_placeholder),
            textColor = colorResource(R.color.payments_placeholder_color)
        ) else ExpirationDateTransformation(),
    )
}