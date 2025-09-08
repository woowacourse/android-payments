package woowacourse.payments.cardaddition.component

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import woowacourse.payments.Card
import woowacourse.payments.ui.component.PaymentCard
import java.lang.Character.isDigit

@Composable
fun CardAdditionScreen(modifier: Modifier = Modifier) {
    val activity: Activity? = LocalActivity.current

    var cardNumber: String by remember { mutableStateOf("") }
    val handleCardNumberInput: (String) -> Unit = { newValue: String ->
        val newCardNumber: String = newValue.filter(::isDigit)
        cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH)
    }

    var expiredDate: String by remember { mutableStateOf("") }
    val handleExpiredDateInput: (String) -> Unit = { newValue: String ->
        val newDate: String = newValue.filter(::isDigit)
        expiredDate = newDate.take(EXPIRED_DATE_LENGTH)
    }

    var name: String by remember { mutableStateOf("") }
    val handleNameInput: (String) -> Unit = { newName: String ->
        name = newName.take(CARD_OWNER_NAME_LENGTH_MAX).uppercase()
    }

    var password: String by remember { mutableStateOf("") }
    val handlePasswordInput = { newValue: String ->
        val newPassword: String = newValue.filter(::isDigit)
        password = newPassword.take(PASSWORD_LENGTH)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CardAdditionTopBar(
                onBackClick = { activity?.finish() },
                onSaveClick = {
                    activity?.setResult(
                        RESULT_OK,
                        Intent().putExtra(
                            "card",
                            Card(
                                number = "",
                                owner = "TODO()",
                                expiredDate = "TODO()",
                            ),
                        ),
                    )
                },
            )
        },
    ) { paddingValues: PaddingValues ->
        Column(
            modifier =
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp),
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
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
            )
            ExpiredDateTextField(
                value = expiredDate,
                onValueChange = handleExpiredDateInput,
                modifier =
                    Modifier
                        .padding(top = 18.dp),
            )
            CardOwnerNameTextField(
                value = name,
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
            )
        }
    }
}

@Preview
@Composable
private fun CardAdditionScreenPreview() {
    CardAdditionScreen()
}
