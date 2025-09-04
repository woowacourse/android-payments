package woowacourse.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
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
import woowacourse.payments.component.CardExpiryDate
import woowacourse.payments.component.CardHolderName
import woowacourse.payments.component.CardNumber
import woowacourse.payments.component.CardPassword
import woowacourse.payments.component.NewCardTopBar
import woowacourse.payments.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class NewCardScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidpaymentsTheme {
                NewCardScreen()
            }
        }
    }
}

@Composable
fun NewCardScreen() {
    var cardNumber by remember { mutableStateOf("") }
    var cardExpiryDate by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    var cardPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { },
                onSaveClick = { },
            )
        }
    )

    Column {
        NewCardTopBar(
            onBackClick = { },
            onSaveClick = { },
        )
        PaymentCard(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 14.dp))
        CardNumber(value = cardNumber, onValueChange = { cardNumber = it })
        CardExpiryDate(value = cardExpiryDate, onValueChange = { cardExpiryDate = it })
        CardHolderName(value = cardHolderName, onValueChange = { cardHolderName = it })
        CardPassword(value = cardPassword, onValueChange = { cardPassword = it })
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    NewCardScreen()
}
