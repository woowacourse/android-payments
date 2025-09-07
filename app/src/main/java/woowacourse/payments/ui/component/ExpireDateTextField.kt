package woowacourse.payments.ui.component

import woowacourse.payments.R
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.core.ExpireDateVisualTransformation
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.Black49

@Composable
fun ExpireDateTextField(
    maxLength: Int,
    expireDate: String,
    onExpireDateChange: (String) -> Unit,
    onComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val expireDateVisualTransformation = ExpireDateVisualTransformation(
        Card.CARD_EXPIRE_DATE_GROUP_SIZE,
        Card.CARD_EXPIRE_DATE_SEPARATOR
    )

    OutlinedTextField(
        value = expireDate,
        onValueChange = { newText ->
            if (newText.length <= maxLength && newText.all { it.isDigit() }) {
                onExpireDateChange(newText)
            }
            if (newText.length == maxLength) {
                onComplete()
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            TextFieldPlaceHolder(textResourceId = R.string.expire_date_place_holder)
        },
        label = {
            Text(
                text = stringResource(R.string.expire_date),
                color = Black49
            )
        },
        singleLine = true,
        visualTransformation = expireDateVisualTransformation,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpireDateTextFieldPreview() {
    ExpireDateTextField(
        maxLength = 4,
        expireDate = "",
        onComplete = {},
        onExpireDateChange = {},
    )
}
