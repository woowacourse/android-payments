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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.CardExpireDateVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardExpireDateField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    supportingTextHeight: Dp = 20.dp,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = "만료일",
        placeholderText = "MM / YY",
        isError = isError,
        supportingText = {
            Box(modifier = Modifier.height(supportingTextHeight)) {
                if (isError) {
                    Text(
                        text = "유효하지 않은 날짜입니다",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        trailingIcon = {
            if (isError) {
                Icon(
                    Icons.Filled.Info,
                    "유효하지 않은 날짜 경고 아이콘",
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
