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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.common.components.PaymentCard
import woowacourse.payments.ui.newcard.components.CardExpirationDateTextField
import woowacourse.payments.ui.newcard.components.CardHolderNameTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.CardPasswordTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import java.time.format.DateTimeFormatter

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (Card) -> Unit = {},
) {
    var cardNumber: String by rememberSaveable { mutableStateOf("") }
    var cardExpirationDate: String by rememberSaveable { mutableStateOf("") }
    var cardHolderName: String by rememberSaveable { mutableStateOf("") }
    var cardPassword: String by rememberSaveable { mutableStateOf("") }
    val card: Card? =
        runCatching {
            Card(
                number = CardNumber.from(cardNumber),
                expirationDate = CardExpirationDate.from(cardExpirationDate, DATE_TIME_FORMATTER),
                holderName = cardHolderName.takeIf { it.isNotBlank() }?.let(::CardHolderName),
                password = CardPassword(cardPassword),
            )
        }.getOrNull()

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            NewCardTopBar(
                canSave = card != null,
                onBackClick = onBackClick,
                onSaveClick = { card?.let { card: Card -> onSaveClick(card) } },
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
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
            )
            CardExpirationDateTextField(
                value = cardExpirationDate,
                onValueChange = { cardExpirationDate = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            CardHolderNameTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                modifier = Modifier.fillMaxWidth(),
            )
            CardPasswordTextField(
                value = cardPassword,
                onValueChange = { cardPassword = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMyy")

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
