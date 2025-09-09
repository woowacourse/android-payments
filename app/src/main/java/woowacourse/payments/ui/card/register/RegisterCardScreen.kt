package woowacourse.payments.ui.card.register

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
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
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.card.component.PaymentCard
import woowacourse.payments.ui.card.register.component.CardExpirationDateTextField
import woowacourse.payments.ui.card.register.component.CardHolderNameTextField
import woowacourse.payments.ui.card.register.component.CardNumberTextField
import woowacourse.payments.ui.card.register.component.CardPasswordTextField
import woowacourse.payments.ui.card.register.component.NewCardTopBar
import woowacourse.payments.ui.model.toUiModel

@Preview
@Composable
fun RegisterCardScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val activity = context as? ComponentActivity

    val onSaveClick: () -> Unit = {
        Card
            .newCard(
                number = cardNumber,
                expirationDate = expirationDate,
                cardHolderName = cardHolderName,
                password = password,
            ).onSuccess { newCard ->
                val newCardUiModel = newCard.toUiModel()
                val resultIntent =
                    Intent().apply {
                        putExtra("new_card_ui_model", newCardUiModel)
                    }
                Toast.makeText(context, "카드가 추가되었습니다.", Toast.LENGTH_SHORT).show()
                activity?.setResult(Activity.RESULT_OK, resultIntent)
                activity?.finish()
            }.onFailure { exception ->
                val errorMessage = exception.message ?: "알 수 없는 오류가 발생했습니다."
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
    }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { activity?.finish() },
                onSaveClick = onSaveClick,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
        ) {
            PaymentCard(
                modifier =
                    Modifier
                        .padding(top = 14.dp)
                        .align(Alignment.CenterHorizontally),
            )
            CardNumberTextField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
            )
            CardExpirationDateTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(0.5f)
                        .defaultMinSize(minWidth = 200.dp),
            )
            CardHolderNameTextField(
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
            )
            CardPasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier =
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.5f)
                        .defaultMinSize(minWidth = 200.dp),
            )
        }
    }
}
