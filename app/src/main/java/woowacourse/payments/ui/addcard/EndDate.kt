package woowacourse.payments.ui.addcard
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import woowacourse.payments.R
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.ui.component.CardExpirationDateVisualTransformation

@Composable
fun EndDate(
    endDate: CardExpirationDate,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = endDate.toCombinedFormat(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.add_card_end_date_placeholder_text)) },
        label = { Text(stringResource(R.string.add_card_end_date_label_text)) },
        isError = endDate.isValid().not(),
        supportingText = {
            Text(" ")
        },
        visualTransformation = CardExpirationDateVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
    )
}