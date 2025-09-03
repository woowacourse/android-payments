package woowacourse.payments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.component.NewCardTopBar
import woowacourse.payments.component.NumberTextField
import woowacourse.payments.component.PaymentCard
import woowacourse.payments.component.StringTextField

@Preview(showBackground = true)
@Composable
fun AddPaymentCardScreen() {
    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = Modifier.padding(bottom = 14.dp),
                onBackClick = { },
                onSaveClick = { }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(28.dp))

            NumberTextField(
                modifier = Modifier.fillMaxWidth(),
                R.string.label_card_number,
                R.string.placeholder_card_number,
                InputType.CardNumber
            )
            NumberTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                R.string.label_expiry,
                R.string.placeholder_expiry,
                InputType.ExpiryDate
            )
            StringTextField(
                modifier = Modifier.fillMaxWidth(),
                R.string.label_owner,
                R.string.placeholder_owner,
            )
            NumberTextField(
                modifier = Modifier.fillMaxWidth(0.6f),
                R.string.label_pin,
                R.string.placeholder_pin,
                InputType.Password
            )
        }
    }
}
