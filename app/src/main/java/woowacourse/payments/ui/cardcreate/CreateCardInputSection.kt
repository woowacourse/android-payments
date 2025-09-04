package woowacourse.payments.ui.cardcreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.cardcreate.model.CreateCardErrorState
import woowacourse.payments.ui.cardcreate.model.CreateCardState
import woowacourse.payments.ui.utils.GroupedSeparatorVisualTransformation
import woowacourse.payments.ui.utils.ext.formatExpiryException
import woowacourse.payments.ui.utils.ext.toErrorMessage

private val CARD_GROUPS = intArrayOf(4, 4, 4, 4)
private val EXPIRY_GROUPS = intArrayOf(2, 2)
private const val OWNER_NAME_MAX = 30
private const val PASSWORD_MAX = 4

private val PaymentSectionSpacing = 30.dp
private val FieldCompactWidth = 146.dp

private const val SEP_CARD = "-"
private const val SEP_EXPIRY = "/"
private val KO_NUMBER = KeyboardOptions(keyboardType = KeyboardType.Number)
private val KO_PIN = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)

@Composable
fun CreateCardInputSection(
    createCardState: CreateCardState,
    createCardErrorState: CreateCardErrorState,
    onCardChange: (CreateCardState) -> Unit,
    onErrorChange: (CreateCardErrorState) -> Unit,
    modifier: Modifier,
) {
    val cardVt = remember { GroupedSeparatorVisualTransformation(CARD_GROUPS, SEP_CARD) }
    val expiryVt = remember { GroupedSeparatorVisualTransformation(EXPIRY_GROUPS, SEP_EXPIRY) }

    Column(
        verticalArrangement = Arrangement.spacedBy(PaymentSectionSpacing),
        modifier = modifier,
    ) {
        CreateCardNumbersInput(
            value = createCardState.cardNumber,
            labelText = stringResource(R.string.card_number_label),
            placeholderText = stringResource(R.string.card_number_placeholder),
            onValueChange = { numbers ->
                onCardChange(createCardState.copy(cardNumber = numbers))
            },
            visualTransformation = cardVt,
            keyboardOptions = KO_NUMBER,
            modifier = Modifier.fillMaxWidth(),
        )

        CreateCardExpiryDateInput(
            value = createCardState.expiryDate,
            labelText = stringResource(R.string.expiry_label),
            placeholderText = stringResource(R.string.expiry_placeholder),
            onValueChange = { expiryDate ->
                val onlyDigits = expiryDate.filter(Char::isDigit).take(4)
                onCardChange(createCardState.copy(expiryDate = onlyDigits))
                if (createCardErrorState.expiryDateMessage != null) {
                    onErrorChange(createCardErrorState.copy(expiryDateMessage = null))
                }
            },
            errorMessage = createCardErrorState.expiryDateMessage,
            visualTransformation = expiryVt,
            keyboardOptions = KO_NUMBER,
            onValidate = { expiryDate ->
                val msg = expiryDate.formatExpiryException()?.toErrorMessage()
                onErrorChange(createCardErrorState.copy(expiryDateMessage = msg))
            },
            modifier = Modifier.width(FieldCompactWidth),
        )

        CreateCardOwnerNameInput(
            value = createCardState.ownerName,
            maxLength = OWNER_NAME_MAX,
            labelText = stringResource(R.string.owner_label),
            placeholderText = stringResource(R.string.owner_placeholder),
            onValueChange = { name ->
                onCardChange(createCardState.copy(ownerName = name.take(OWNER_NAME_MAX)))
            },
            modifier = Modifier.fillMaxWidth(),
        )

        CreateCardPasswordInput(
            value = createCardState.password,
            labelText = stringResource(R.string.password_label),
            placeholderText = stringResource(R.string.password_placeholder),
            onValueChange = { password ->
                onCardChange(createCardState.copy(password = password.take(PASSWORD_MAX)))
            },
            keyboardOptions = KO_PIN,
            modifier = Modifier.width(FieldCompactWidth),
        )
    }
}
