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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.components.AppTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.AppTestTags.CARD_OWNER_NAME_FIELD

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardOwnerNameField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    supportingText: @Composable (() -> Unit)? = null,
) {
    AppTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.testTag(CARD_OWNER_NAME_FIELD),
        labelText = stringResource(R.string.add_card_owner_name_field_title),
        placeholderText = stringResource(R.string.add_card_owner_name_field_hint),
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
                    textAlign = TextAlign.End,
                )
            },
        )
    }
}
