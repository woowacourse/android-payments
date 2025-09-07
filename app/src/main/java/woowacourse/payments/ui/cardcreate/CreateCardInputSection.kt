package woowacourse.payments.ui.cardcreate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cardcreate.CreateCardStateHolder.Companion.CARD_OWNER_NAME_MAX
import woowacourse.payments.ui.cardcreate.model.CreateCardState
import woowacourse.payments.ui.utils.GroupedSeparatorVisualTransformation

private val CARD_GROUPS = intArrayOf(4, 4, 4, 4)
private val EXPIRY_GROUPS = intArrayOf(2, 2)

private const val SEP_CARD = "-"
private const val SEP_EXPIRY = "/"
private val KEYBOARD_OPTIONS_NUMBER = KeyboardOptions(keyboardType = KeyboardType.Number)
private val KEYBOARD_OPTIONS_PIN = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)

@Composable
fun CreateCardInputSection(
    createCardState: CreateCardState,
    onCardNumbersChange: (String) -> Unit,
    onCardExpiryDateChange: (String) -> Unit,
    onCardOwnerNameChange: (String) -> Unit,
    onCardPasswordChange: (String) -> Unit,
    modifier: Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = createCardState.cardNumber,
            onValueChange = onCardNumbersChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.card_number_label)) },
            placeholder = { Text(stringResource(R.string.card_number_placeholder)) },
            visualTransformation = GroupedSeparatorVisualTransformation(CARD_GROUPS, SEP_CARD),
            keyboardOptions = KEYBOARD_OPTIONS_NUMBER,
        )

        val expiryDateErrorTextRes =
            createCardState.expiryDateErrorTextRes?.let { stringResource(it) }

        OutlinedTextField(
            value = createCardState.expiryDate,
            onValueChange = onCardExpiryDateChange,
            modifier = Modifier.width(146.dp),
            label = { Text(stringResource(R.string.expiry_label)) },
            isError = expiryDateErrorTextRes != null,
            placeholder = { Text(stringResource(R.string.expiry_placeholder)) },
            supportingText = {
                Text(
                    expiryDateErrorTextRes ?: "",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            },
            visualTransformation = GroupedSeparatorVisualTransformation(EXPIRY_GROUPS, SEP_EXPIRY),
            keyboardOptions = KEYBOARD_OPTIONS_NUMBER,
        )

        OutlinedTextField(
            value = createCardState.ownerName,
            onValueChange = onCardOwnerNameChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.card_number_label)) },
            placeholder = { Text(stringResource(R.string.card_number_placeholder)) },
            supportingText = {
                Text(
                    "${createCardState.ownerName.length}/$CARD_OWNER_NAME_MAX",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            }
        )

        OutlinedTextField(
            value = createCardState.password,
            onValueChange = onCardPasswordChange,
            modifier = Modifier.width(146.dp),
            label = { Text(stringResource(R.string.card_number_label)) },
            placeholder = { Text(stringResource(R.string.card_number_placeholder)) },
            keyboardOptions = KEYBOARD_OPTIONS_PIN,
        )
    }
}

@Composable
fun PaymentCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = colorResource(id = R.color.gray_33),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = colorResource(id = R.color.yellow_CB),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
    }
}
