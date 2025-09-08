package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import woowacourse.payments.ui.components.CardNumberField
import woowacourse.payments.ui.components.ExpirationDateField
import woowacourse.payments.ui.components.NewCardTopBar
import woowacourse.payments.ui.components.PasswordField
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.UserNameField

@Preview
@Composable
fun AddCardScreen() {
    var number by remember { mutableStateOf("") }
    var expiration by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { NewCardTopBar(onBackClick = {}, onSaveClick = {}) },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
        ) {
            Spacer(Modifier.height(14.dp))
            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                cardInfo = null,
            )

            Spacer(Modifier.height(40.dp))
            CardNumberField(
                value = number,
                onValueChange = { number = it },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(30.dp))
            ExpirationDateField(
                value = expiration,
                onValueChange = { expiration = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )

            Spacer(Modifier.height(30.dp))
            UserNameField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(18.dp))
            PasswordField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(0.5f),
            )
        }
    }
}
