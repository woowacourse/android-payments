package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardExpireDateField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = "만료일",
        placeholderText = "MM / YY",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
