package woowacourse.payments.component

import woowacourse.payments.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardOwnerTextField(
    maxLength: Int,
    ownerName: String,
    onChangeOwerName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Column {
            CardOwnerEditText(
                maxLength = maxLength,
                ownerName = ownerName,
                onChangeOwerName = { onChangeOwerName(it) }
            )

            Text(
                text = "${ownerName.length} / $maxLength",
                modifier = Modifier
                    .padding(top = 4.dp)
                    .align(Alignment.End)
            )
        }

        TextFieldLabel(stringResource(R.string.card_owner))
    }
}

@Composable
fun CardOwnerEditText(
    maxLength: Int,
    ownerName: String,
    onChangeOwerName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = ownerName,
        onValueChange = { newText ->
            if (newText.length <= maxLength) {
                onChangeOwerName(newText)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        placeholder = {
            TextFieldPlaceHolder(textResourceId = R.string.card_owner_place_holder)
        },
        modifier = modifier
            .padding(top = 14.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun CardOwnerTextFieldPreview() {
    CardOwnerTextField(
        ownerName = "페토",
        maxLength = 10,
        onChangeOwerName = {}
    )
}
