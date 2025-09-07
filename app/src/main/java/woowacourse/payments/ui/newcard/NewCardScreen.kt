package woowacourse.payments.ui.newcard

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity.Companion.EXTRA_NEW_CARD
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.ExpirationDateTextField
import woowacourse.payments.ui.newcard.components.NameTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PasswordField
import woowacourse.payments.ui.newcard.components.PaymentCardBox
import woowacourse.payments.ui.util.extensions.getActivity

@Composable
fun NewCardScreen() {
    val activity = LocalContext.current.getActivity()
    val context = LocalContext.current
    var cardNumber: String by rememberSaveable { mutableStateOf("") }
    var cardHolder: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var expirationDateUitState by rememberSaveable { mutableStateOf(ExpirationDateUiState("")) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = { activity?.finish() },
                onSaveClick = {
                    val resultIntent =
                        Intent().apply {
                            putExtra(
                                EXTRA_NEW_CARD,
                                PaymentCardUiModel(
                                    cardNumber = cardNumber,
                                    cardHolder = cardHolder,
                                    expirationDate = expirationDateUitState.expirationDate,
                                ),
                            )
                        }
                    activity?.setResult(RESULT_OK, resultIntent)
                    activity?.finish()
                    Toast.makeText(context, R.string.new_card_add_card_success, Toast.LENGTH_SHORT).show()
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            PaymentCardBox(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp),
            )
            CardNumberTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 24.dp, end = 24.dp),
                value = cardNumber,
                onValueChange = { cardNumber = it },
                maxLength = 16,
            )
            ExpirationDateTextField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = expirationDateUitState.expirationDate.value,
                onValueChange = { expirationDateUitState.onValueChanged(it) },
                isValid = expirationDateUitState.isError,
                maxLength = 4,
            )
            NameTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 30.dp, end = 24.dp),
                value = cardHolder,
                onValueChange = { cardHolder = it },
                maxLength = 30,
            )
            PasswordField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = password,
                onValueChange = { password = it },
                maxLength = 4,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
