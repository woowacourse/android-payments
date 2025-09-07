package woowacourse.payments.newcard

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.newcard.component.CardNumberTextField
import woowacourse.payments.newcard.component.ExpiredDateTextField
import woowacourse.payments.newcard.component.NewCardTopBar
import woowacourse.payments.newcard.component.OwnerNameTextField
import woowacourse.payments.newcard.component.PasswordTextField
import woowacourse.payments.newcard.component.PaymentCard

@Preview
@Composable
fun NewCardScreen(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    var cardNumber: String by remember { mutableStateOf("") }
    var expiredDate: String by remember { mutableStateOf("") }
    var ownerName: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
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
