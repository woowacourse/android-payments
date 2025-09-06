package woowacourse.payments.component

import woowacourse.payments.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.Black49

@Composable
fun CardOwnerTextField(
    maxLength: Int,
    ownerName: String,
    onChangeOwnerName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = ownerName,
        onValueChange = { newText ->
            if (newText.length <= maxLength && newText.all { it.isLetter() }) {
                onChangeOwnerName(newText)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        placeholder = {
            TextFieldPlaceHolder(textResourceId = R.string.card_owner_place_holder)
        },
        label = {
            Text(
                text = stringResource(R.string.card_owner),
                color = Black49
            )
        },
        singleLine = true,
        supportingText = {
            Text(
                text = stringResource(R.string.card_owner_letter_count).format(
                    ownerName.length,
                    maxLength
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun CardOwnerTextFieldPreview() {
    CardOwnerTextField(
        ownerName = "페토",
        maxLength = 10,
        onChangeOwnerName = {}
    )
}
