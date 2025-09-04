package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.newcard.components.CardExpirationDateTextField
import woowacourse.payments.ui.newcard.components.CardHolderNameTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.CardPasswordTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PaymentCard

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    var cardNumber: String by remember { mutableStateOf("") }
    var cardExpirationDate: String by remember { mutableStateOf("") }
    var cardHolderName: String by remember { mutableStateOf("") }
    var cardPassword: String by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
    ) { innerPadding: PaddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = innerPadding.calculateTopPadding())
                    .padding(horizontal = 24.dp, vertical = 20.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp),
            )
            CardNumberTextField(
                cardNumber = cardNumber,
                maxLength = CardNumber.CARD_NUMBER_LENGTH,
                onValueChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
            )
            CardExpirationDateTextField(
                cardExpirationDate = cardExpirationDate,
                maxLength = 4,
                onValueChange = { cardExpirationDate = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            CardHolderNameTextField(
                cardHolderName = cardHolderName,
                maxLength = 30,
                onValueChange = { cardHolderName = it },
                modifier = Modifier.fillMaxWidth(),
            )
            CardPasswordTextField(
                cardPassword = cardPassword,
                maxLength = 4,
                onValueChange = { cardPassword = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

@Preview
@Composable
fun NewCardScreenPreview() {
    NewCardScreen()
}
