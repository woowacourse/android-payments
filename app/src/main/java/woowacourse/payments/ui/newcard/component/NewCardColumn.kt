package woowacourse.payments.ui.newcard.component

import android.R.attr.password
import androidx.compose.foundation.clickable
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
import woowacourse.payments.ui.cardcatalog.component.PaymentCard
import woowacourse.payments.ui.newcard.state.CardStateHolder

@Composable
fun NewCardColumn(
    stateHolder: CardStateHolder,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        PaymentCard(
            card = null,
            cardCompanyUiModel = stateHolder.uiState.cardCompanyUiModel,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp, bottom = 28.dp)
                .clickable(
                    onClick = { stateHolder.changeBottomSheetState() }
                ),
        )
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardNumberTextField(
                number = stateHolder.uiState.number,
                numberErrorMessage = stateHolder.uiState.numberErrorMessage,
                onNumberChange = { stateHolder.changeNumber(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            ExpiredDateTextField(
                expiredDate = stateHolder.uiState.expirationDate,
                expirationDateErrorMessage = stateHolder.uiState.expirationDateErrorMessage,
                onExpirationDateChange = { stateHolder.changeExpirationDate(it) },
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardOwnerNameTextField(
                ownerName = stateHolder.uiState.ownerName,
                ownerNameErrorMessage = stateHolder.uiState.ownerNameErrorMessage,
                onOwnerNameChange = { stateHolder.changeOwnerName(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            PasswordTextField(
                password = stateHolder.uiState.password,
                passwordErrorMessage = stateHolder.uiState.passwordErrorMessage,
                onPasswordChange = { stateHolder.changePassword(it) }
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


