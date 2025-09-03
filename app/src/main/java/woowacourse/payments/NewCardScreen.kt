package woowacourse.payments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
                CardNumberTextField(modifier = Modifier.fillMaxWidth())
                ExpiredDateTextField()
                OwnerNameTextField(modifier = Modifier.fillMaxWidth())
                PasswordTextField()
            }
        }
    }
}
