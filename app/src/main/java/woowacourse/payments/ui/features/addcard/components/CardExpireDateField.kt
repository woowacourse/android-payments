package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.card.values.ExpireDate.Companion.MAX_LENGTH_EXPIRE_DATE
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.features.addcard.ExpireDateUiState
import woowacourse.payments.ui.mapper.messageResId
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.SeparatorVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardExpireDateField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    expireDateUiState: ExpireDateUiState,
    supportingTextHeight: Dp = 20.dp,
) {
    var isFocused by remember { mutableStateOf(false) }
    val showError =
        expireDateUiState is ExpireDateUiState.Invalid || expireDateUiState is ExpireDateUiState.Typing && !isFocused
    val visualTransformation = remember { SeparatorVisualTransformation(2, " / ") }

    AppTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { it in '0'..'9' }.take(MAX_LENGTH_EXPIRE_DATE)
            onValueChange(filteredValue)
        },
        modifier =
            modifier.onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        labelText = stringResource(R.string.add_card_expire_date_field_title),
        placeholderText = stringResource(R.string.add_card_expire_date_field_hint),
        isError = showError,
        supportingText = {
            Box(modifier = Modifier.height(supportingTextHeight)) {
                if (showError && expireDateUiState is ExpireDateUiState.Invalid) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = expireDateUiState.reason.messageResId),
                        color = MaterialTheme.colorScheme.error,
                    )
                } else if (showError && expireDateUiState is ExpireDateUiState.Typing) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.add_card_expire_date_incomplete_error_message),
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
fun CardExpireDateFieldPreview() {
    var text by remember { mutableStateOf("") }
    AndroidpaymentsTheme(dynamicColor = false) {
        CardExpireDateField(
            value = text,
            onValueChange = { text = it },
            expireDateUiState = ExpireDateUiState.Empty,
        )
    }
}
