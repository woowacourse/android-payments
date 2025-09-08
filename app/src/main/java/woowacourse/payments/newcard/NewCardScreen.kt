package woowacourse.payments.newcard

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.cards.CardParcelable
import woowacourse.payments.cards.toParcelable
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.newcard.component.CardNumberTextField
import woowacourse.payments.newcard.component.ExpiredDateTextField
import woowacourse.payments.newcard.component.NewCardTopBar
import woowacourse.payments.newcard.component.OwnerNameTextField
import woowacourse.payments.newcard.component.PasswordTextField
import woowacourse.payments.util.PaymentCard

@Preview
@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: (CardParcelable) -> Unit = {},
) {
    val context = LocalContext.current

    var cardNumber: String by remember { mutableStateOf("") }
    var expiredDate: String by remember { mutableStateOf("") }
    var ownerName: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    val card: Card? = makeCard(cardNumber, expiredDate, ownerName, password)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    if (card != null) {
                        onSaveClick(card.toParcelable())
                    } else {
                        Toast.makeText(context, "입력란을 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            PaymentCard(modifier = Modifier.padding(top = 14.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 24.dp, horizontal = 40.dp),
            ) {
                CardNumberTextField(
                    value = cardNumber,
                    onValueChange = { cardNumber = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                ExpiredDateTextField(
                    value = expiredDate,
                    onValueChange = { expiredDate = it },
                )
                OwnerNameTextField(
                    value = ownerName,
                    onValueChange = { ownerName = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                )
            }
        }
    }
}

private fun String.extractMonth(): Int? =
    if (length < 2) {
        null
    } else {
        take(2).toIntOrNull()
    }

private fun String.extractYear(): Int? =
    if (length < 4) {
        null
    } else {
        takeLast(2).toIntOrNull()
    }

private fun makeCard(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
): Card? {
    return try {
        val cardNumber = CardNumber(cardNumber)
        val expiredMonth = expiredDate.extractMonth() ?: return null
        val expiredYear = expiredDate.extractYear() ?: return null
        val expiredDate = ExpiredDate.of(expiredMonth, expiredYear) ?: return null
        val ownerName = OwnerName(ownerName)

        Card(cardNumber, expiredDate, ownerName, password)
    } catch (e: Throwable) {
        null
    }
}
