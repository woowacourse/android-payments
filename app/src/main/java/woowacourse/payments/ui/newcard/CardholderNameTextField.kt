package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import woowacourse.payments.R

private const val CARDHOLDER_NAME_MAXIMUM_LENGTH = 30

@Suppress("ktlint:standard:function-naming")
@Composable
fun CardHolderNameTextField(text: MutableState<String>) {
    val focusManager = LocalFocusManager.current

    CardInfoTextFields(
        modifier = Modifier.fillMaxWidth(),
        value = text.value,
        label = stringResource(R.string.cardholder_name_label),
        placeholder = stringResource(R.string.cardholder_name_placeholder),
        supportingText = {
            Text(
                text = "${text.value.length} / 30",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardActions =
            KeyboardActions(onDone = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
    ) { newValue: String ->
        text.value = newValue.take(CARDHOLDER_NAME_MAXIMUM_LENGTH)
    }
}
