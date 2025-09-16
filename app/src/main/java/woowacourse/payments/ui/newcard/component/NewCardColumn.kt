package woowacourse.payments.ui.newcard.component

import android.R.attr.password
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.newcard.CardStateHolder
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel

@Composable
fun NewCardColumn(
    state: CardStateHolder,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        BankTypeCard(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp, bottom = 28.dp),
            bankTypeUiModel = state.bankTypeUiModel
        )
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardNumberTextField(
                number = state.number,
                numberErrorMessage = state.numberErrorMessage,
                onNumberChange = { state.changeNumber(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            ExpiredDateTextField(
                expiredDate = state.expirationDate,
                expirationDateErrorMessage = state.expirationDateErrorMessage,
                onExpirationDateChange = { state.changeExpirationDate(it) },
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardOwnerNameTextField(
                ownerName = state.ownerName,
                ownerNameErrorMessage = state.ownerNameErrorMessage,
                onOwnerNameChange = { state.changeOwnerName(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            PasswordTextField(
                password = state.password,
                passwordErrorMessage = state.passwordErrorMessage,
                onPasswordChange = { state.changePassword(it) }
            )
        }
    }
}

@Preview
@Composable
private fun NewCardColumnPreview() {
    NewCardColumn(
        CardStateHolder()
    )
}


