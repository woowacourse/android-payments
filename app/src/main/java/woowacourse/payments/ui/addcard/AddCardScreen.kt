package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.addcard.component.CardNumberTextField
import woowacourse.payments.ui.addcard.component.ExpireDateTextField
import woowacourse.payments.ui.addcard.component.OwnerNameTextField
import woowacourse.payments.ui.addcard.component.PasswordTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(innerPadding: PaddingValues, cardInfo: CardInfoUiState) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(top = 14.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(CardInfoUiState())
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            CardNumberTextField(
                cardInfo = cardInfo,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(18.dp))
            ExpireDateTextField(
                cardInfo = cardInfo,
                modifier = Modifier
                    .fillMaxWidth(0.47f),
            )
            OwnerNameTextField(
                cardInfo = cardInfo,
                modifier = Modifier
                    .fillMaxWidth(),
            )
            PasswordTextField(
                cardInfo = cardInfo,
                modifier = Modifier.fillMaxWidth(0.47f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            topBar = {
                AddCardTopbar()
            }
        ) {
            AddCardScreen(it, CardInfoUiState())
        }
    }
}