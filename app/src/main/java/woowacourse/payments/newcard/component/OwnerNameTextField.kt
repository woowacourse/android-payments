package woowacourse.payments.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.domain.OwnerName

@Composable
fun OwnerNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { value: String ->
            if (isValidInput(value)) {
                onValueChange(value)
            }
        },
        label = { Text(stringResource(R.string.owner_name)) },
        placeholder = {
            Text(
                text = stringResource(R.string.owner_name_placeholder),
                color = Color.Gray,
            )
        },
        singleLine = true,
        supportingText = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "${value.length}/${OwnerName.MAX_LENGTH}",
                )
            }
        },
        modifier = modifier,
    )
}

private fun isValidInput(ownerName: String): Boolean = ownerName.length <= OwnerName.MAX_LENGTH
