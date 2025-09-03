package woowacourse.payments.component

import woowacourse.payments.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.core.ExpireDateVisualTransformation
import woowacourse.payments.ui.theme.GrayAA

@Composable
fun ExpireDateTextField(
    maxLength: Int,
    expireDate: String,
    onExpireDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.5f)
    ) {
        ExpireDateEditText(
            maxLength = maxLength,
            expireDate = expireDate,
            onExpireDateChange = onExpireDateChange,
            modifier = Modifier
                .padding(top = 14.dp)
                .wrapContentSize()
        )
        TextFieldLabel(stringResource(R.string.expire_date))
    }
}

@Composable
fun ExpireDateEditText(
    maxLength: Int,
    expireDate: String,
    onExpireDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val expireDateVisualTransformation = ExpireDateVisualTransformation()

    OutlinedTextField(
        value = expireDate,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onExpireDateChange(newText)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        placeholder = {
            Text(
                color = GrayAA,
                text = stringResource(R.string.expire_date_place_holder)
            )
        },
        visualTransformation = expireDateVisualTransformation,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ExpireDateTextFieldPreview() {
    ExpireDateTextField(
        maxLength = 4,
        expireDate = "",
        onExpireDateChange = {}
    )
}
