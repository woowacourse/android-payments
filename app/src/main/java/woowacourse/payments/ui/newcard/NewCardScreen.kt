package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.common.components.PaymentCard
import woowacourse.payments.ui.newcard.components.CardExpirationDateTextField
import woowacourse.payments.ui.newcard.components.CardHolderNameTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.CardPasswordTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (Card) -> Unit = {},
    state: NewCardState = rememberNewCardState(),
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            NewCardTopBar(
                canSave = state.card != null,
                onBackClick = onBackClick,
                onSaveClick = { state.card?.let { card: Card -> onSaveClick(card) } },
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .verticalScroll(scrollState),
        ) {
            PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            CardNumberTextField(
                value = state.cardNumber,
                onValueChange = state::onCardNumberChange,
                isValid = state.isCardNumberValid,
                modifier = Modifier.fillMaxWidth(),
            )
            CardExpirationDateTextField(
                value = state.cardExpirationDate,
                onValueChange = state::onCardExpirationDateChange,
                isValid = state.isCardExpirationDateValid,
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            CardHolderNameTextField(
                value = state.cardHolderName,
                onValueChange = state::onCardHolderNameChange,
                isValid = state.isCardHolderNameValid,
                modifier = Modifier.fillMaxWidth(),
            )
            CardPasswordTextField(
                value = state.cardPassword,
                onValueChange = state::onCardPasswordChange,
                isValid = state.isCardPasswordValid,
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
