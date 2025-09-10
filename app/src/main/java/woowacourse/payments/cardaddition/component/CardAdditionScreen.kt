package woowacourse.payments.cardaddition.component

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.Card
import woowacourse.payments.ui.component.PaymentCard
import java.lang.Character.isDigit
import java.time.Month

private const val PASSWORD_LENGTH: Int = 4

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    val activity: Activity? = LocalActivity.current
    val scrollState = rememberScrollState()

    var cardNumber: String by rememberSaveable { mutableStateOf("") }
    val handleCardNumberInput: (String) -> Unit = { newValue: String ->
        val newCardNumber: String = newValue.filter(::isDigit)
        cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH)
    }

    var expiredDate: String by rememberSaveable { mutableStateOf("") }
    val handleExpiredDateInput: (String) -> Unit = { newValue: String ->
        val newDate: String = newValue.filter(::isDigit)
        expiredDate = newDate.take(EXPIRED_DATE_LENGTH)
    }

    var ownerName: String by rememberSaveable { mutableStateOf("") }
    val handleNameInput: (String) -> Unit = { newName: String ->
        ownerName = newName.take(CARD_OWNER_NAME_LENGTH_MAX).uppercase()
    }

    var password: String by rememberSaveable { mutableStateOf("") }
    val handlePasswordInput = { newValue: String ->
        val newPassword: String = newValue.filter(::isDigit)
        password = newPassword.take(PASSWORD_LENGTH)
    }

    Scaffold(
        modifier = modifier.testTag("CardAdditionScreen"),
        topBar = {
            CardAdditionTopAppBar(
                completable =
                    cardNumber.isValidCardNumber &&
                        expiredDate.isValidExpiredDate &&
                        password.isValidPassword,
                onBackClick = { activity?.finish() },
                onSaveClick = {
                    activity?.setResult(
                        RESULT_OK,
                        Intent().putExtra(
                            "card",
                            Card(
                                number = cardNumber,
                                owner = ownerName,
                                expiredDate = expiredDate,
                            ),
                        ),
                    )

                    activity?.finish()
                },
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp, bottom = 28.dp),
            )
            CardNumberTextField(
                value = cardNumber,
                onValueChange = handleCardNumberInput,
                isError = cardNumber.isNotBlank() && !cardNumber.isValidCardNumber,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
            )
            ExpiredDateTextField(
                value = expiredDate,
                onValueChange = handleExpiredDateInput,
                isError = expiredDate.isNotBlank() && !expiredDate.isValidExpiredDate,
                modifier =
                    Modifier
                        .padding(top = 18.dp),
            )
            CardOwnerNameTextField(
                value = ownerName,
                onValueChange = handleNameInput,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 18.dp),
            )
            PasswordTextField(
                value = password,
                onValueChange = handlePasswordInput,
                isError = password.isNotBlank() && !password.isValidPassword,
            )
        }
    }
}

private val String.isValidCardNumber: Boolean get() = length == CARD_NUMBER_LENGTH
private val String.isValidExpiredDate: Boolean
    get() {
        val month: Int = take(2).toIntOrNull() ?: return false
        return length == EXPIRED_DATE_LENGTH && month in Month.entries.map(Month::getValue)
    }
private val String.isValidPassword: Boolean get() = length == PASSWORD_LENGTH

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}
