package woowacourse.payments.ui.newcard.component

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
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.ui.cardlist.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.newcard.state.CardUiState

@Composable
fun NewCardColumn(
    uiState: CardUiState,
    selectCardCompany: () -> Unit,
    changeNumber: (String) -> Unit,
    changeExpiredDate: (String) -> Unit,
    changeOwnerName: (String) -> Unit,
    changePassword: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp),
    ) {
        PaymentCard(
            onEditCard = { selectCardCompany() },
            cardUiModel = uiState.cardUiModel,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardNumberTextField(
                number = uiState.number,
                numberErrorMessage = uiState.numberErrorMessage,
                onNumberChange = { changeNumber(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            ExpiredDateTextField(
                expiredDate = uiState.expiredDate,
                expirationDateErrorMessage = uiState.expirationDateErrorMessage,
                onExpirationDateChange = { changeExpiredDate(it) },
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            CardOwnerNameTextField(
                ownerName = uiState.ownerName,
                ownerNameErrorMessage = uiState.ownerNameErrorMessage,
                onOwnerNameChange = { changeOwnerName(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Box(
            modifier = Modifier.height(86.dp)
        ) {
            PasswordTextField(
                password = uiState.password,
                passwordErrorMessage = uiState.passwordErrorMessage,
                onPasswordChange = { changePassword(it) }
            )
        }
    }
}

@Preview
@Composable
private fun NewCardColumnPreview() {
    NewCardColumn(
        CardStateHolder().uiState.value, {}, {}, {}, {}, {}
    )
}


