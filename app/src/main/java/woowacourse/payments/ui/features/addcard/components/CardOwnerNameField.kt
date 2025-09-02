package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOwnerNameField(
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        labelText = "카드 소유자 이름(선택)",
        placeholderText = "카드에 표시된 이름을 입력하세요.",
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Preview(showBackground = true)
@Composable
fun CardOwnerNameFieldPreview() {
    var text by remember { mutableStateOf("") }

    AndroidpaymentsTheme(dynamicColor = false) {
        CardOwnerNameField(
            value = text,
            onValueChange = { text = it },
            supportingText = {
                Text(
                    text = "${text.length}/30",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
        )
    }
}