package woowacourse.payments.card.register

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
import woowacourse.payments.card.register.component.CardExpirationDateTextField
import woowacourse.payments.card.register.component.CardHolderNameTextField
import woowacourse.payments.card.register.component.CardNumberTextField
import woowacourse.payments.card.register.component.CardPasswordTextField
import woowacourse.payments.card.register.component.NewCardTopBar
import woowacourse.payments.card.register.component.PaymentCard

@Preview
@Composable
fun NewCardScreen() {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { /* 뒤로 가기 로직 */ },
                onSaveClick = { /* 저장 로직 */ },
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
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
            )
            CardExpirationDateTextField(
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(0.5f),
            )
            CardHolderNameTextField(
                modifier =
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
            )
            CardPasswordTextField(
                modifier =
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.5f),
            )
        }
    }
}
