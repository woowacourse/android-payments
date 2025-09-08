package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardDigit
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.ui.components.LimitedLengthOutlinedTextField
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.transformation.GroupedVisualTransformation
import java.time.YearMonth
import java.time.format.DateTimeFormatter

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
                onSaveClick = {
                    try {
                        Card(
                            number = CardNumber.from(cardNumber),
                            expirationDate =
                                CardExpirationDate.from(
                                    cardExpirationDate,
                                    DATE_TIME_FORMATTER,
                                ),
                            holderName = CardHolderName(cardHolderName),
                            password = CardPassword(cardPassword),
                        )
                        onSaveClick()
                    } catch (e: IllegalArgumentException) {
                        return@NewCardTopBar
                    }
                },
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
            PaymentCard(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(20.dp))

            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
            )
            LimitedLengthOutlinedTextField(
                value = cardExpirationDate,
                onValueChange = { cardExpirationDate = it },
                maxLength = 4,
                label = { Text(stringResource(R.string.card_expiration_date)) },
                placeholder = { Text("MM / YY") },
                isError =
                    cardExpirationDate.isNotEmpty() &&
                        runCatching {
                            CardExpirationDate.from(
                                cardExpirationDate,
                                DATE_TIME_FORMATTER,
                            )
                        }.isFailure,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation =
                    GroupedVisualTransformation(
                        List(2) { EXPIRATION_DATE_GROUP_SIZE },
                        " / ",
                    ),
                inputFilter = { it.filter(Char::isDigit) },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
            LimitedLengthOutlinedTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                maxLength = CardHolderName.MAX_NAME_LENGTH,
                label = { Text(stringResource(R.string.card_holder_name)) },
                placeholder = { Text(stringResource(R.string.input_card_holder_name)) },
                isError = cardHolderName.isNotEmpty() && runCatching { CardHolderName(cardHolderName) }.isFailure,
                supportingText = {
                    Text(
                        "${cardHolderName.length}/${CardHolderName.MAX_NAME_LENGTH}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                inputFilter = {
                    it.uppercase().filter { ch -> ch.isLetter() || ch.isWhitespace() }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            LimitedLengthOutlinedTextField(
                value = cardPassword,
                onValueChange = { cardPassword = it },
                maxLength = CardPassword.CARD_PASSWORD_LENGTH,
                label = { Text(stringResource(R.string.card_password)) },
                placeholder = { Text("0000") },
                isError = cardPassword.isNotEmpty() && runCatching { CardPassword(cardPassword) }.isFailure,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                visualTransformation = PasswordVisualTransformation(),
                inputFilter = { it.filter(Char::isDigit) },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}

private const val EXPIRATION_DATE_GROUP_SIZE = 2
private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMyy")

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
