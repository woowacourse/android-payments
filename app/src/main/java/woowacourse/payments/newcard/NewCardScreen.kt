package woowacourse.payments.newcard

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.cards.CardParcelable
import woowacourse.payments.cards.toParcelable
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.newcard.component.CardNumberTextField
import woowacourse.payments.newcard.component.ExpiredDateTextField
import woowacourse.payments.newcard.component.NewCardTopBar
import woowacourse.payments.newcard.component.OwnerNameTextField
import woowacourse.payments.newcard.component.PasswordTextField
import woowacourse.payments.util.PaymentCard

@Preview
@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    newCardStateHolder: NewCardStateHolder = rememberSaveable { NewCardStateHolder() },
    onBackClick: () -> Unit = {},
    onSaveClick: (CardParcelable) -> Unit = {},
    onCardSaveFailed: () -> Unit = {},
) {
    val card: Card? =
        with(newCardStateHolder) {
            makeCard(cardNumber, expiredDate, ownerName, password)
        }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    if (card != null) {
                        onSaveClick(card.toParcelable())
                    } else {
                        onCardSaveFailed
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
                    value = newCardStateHolder.cardNumber,
                    onValueChange = newCardStateHolder::updateCardNumber,
                    modifier = Modifier.fillMaxWidth(),
                )
                ExpiredDateTextField(
                    value = newCardStateHolder.expiredDate,
                    onValueChange = newCardStateHolder::updateExpiredDate,
                )
                OwnerNameTextField(
                    value = newCardStateHolder.ownerName,
                    onValueChange = newCardStateHolder::updateOwnerName,
                    modifier = Modifier.fillMaxWidth(),
                )
                PasswordTextField(
                    value = newCardStateHolder.password,
                    onValueChange = newCardStateHolder::updatePassword,
                )
            }
        }
    }
}

private fun makeCard(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
): Card? {
    return try {
        val cardNumber = CardNumber(cardNumber)
        val expiredDate = ExpiredDate.of(expiredDate) ?: return null
        val ownerName = OwnerName(ownerName)
        val password = Password(password)

        Card(cardNumber, expiredDate, ownerName, password)
    } catch (e: IllegalArgumentException) {
        Log.e("NewCardScreen", "makeCard: $e")
        null
    }
}
