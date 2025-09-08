package woowacourse.payments.ui.newcard

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import woowacourse.payments.ui.newcard.components.CardExpirationDateTextField
import woowacourse.payments.ui.newcard.components.CardHolderNameTextField
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.CardPasswordTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.transformation.GroupedVisualTransformation
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    val context = LocalContext.current

    var cardNumber: String by remember { mutableStateOf("") }
    var cardExpirationDate: String by remember { mutableStateOf("") }
    var cardHolderName: String by remember { mutableStateOf("") }
    var cardPassword: String by remember { mutableStateOf("") }

    fun buildCardOrNull(): Card? =
        runCatching {
            Card(
                number = CardNumber.from(cardNumber),
                expirationDate = CardExpirationDate.from(cardExpirationDate, DATE_TIME_FORMATTER),
                holderName = cardHolderName.takeIf { it.isNotBlank() }?.let(::CardHolderName),
                password = CardPassword(cardPassword),
            )
        }.getOrNull()

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    buildCardOrNull()?.let {
                        onSaveClick()
                        Toast
                            .makeText(context, "카드가 추가되었습니다", Toast.LENGTH_SHORT)
                            .show()
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
