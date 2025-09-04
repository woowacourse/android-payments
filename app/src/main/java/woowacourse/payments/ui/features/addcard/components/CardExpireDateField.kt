package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.CardExpireDateVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardExpireDateField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    expireDateStatus: ExpireDateStatus = ExpireDateStatus.Valid,
    supportingTextHeight: Dp = 20.dp,
) {
    val isError = expireDateStatus != ExpireDateStatus.Valid && expireDateStatus != ExpireDateStatus.Typing

    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = stringResource(R.string.add_card_expire_date_field_title),
        placeholderText = stringResource(R.string.add_card_expire_date_field_hint),
        isError = isError,
        supportingText = {
            Box(modifier = Modifier.height(supportingTextHeight)) {
                when (expireDateStatus) {
                    ExpireDateStatus.Expired ->
                        Text(
                            text = stringResource(R.string.add_card_expire_date_past_error_message),
                            color = MaterialTheme.colorScheme.error,
                        )

                    ExpireDateStatus.InvalidMonth ->
                        Text(
                            text = stringResource(R.string.add_card_expire_date_month_error_message),
                            color = MaterialTheme.colorScheme.error,
                        )

                    ExpireDateStatus.Valid -> return@Box
                    ExpireDateStatus.Typing -> return@Box
                    ExpireDateStatus.Invalid ->
                        Text(
                            text = stringResource(R.string.add_card_expire_date_etc_error_message),
                            color = MaterialTheme.colorScheme.error,
                        )
                }
            }
        },
        trailingIcon = {
            if (isError) {
                Icon(
                    Icons.Filled.Info,
                    stringResource(R.string.add_card_expire_date_icon_description),
                    tint = MaterialTheme.colorScheme.error,
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = CardExpireDateVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
fun CardExpireDateFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardExpireDateField(
            value = text,
            onValueChange = {
                text = it
            },
        )
    }
}
