package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.model.CardInfo
import woowacourse.payments.ui.addcard.util.CardNumberTransformation
import woowacourse.payments.ui.addcard.util.ExpirationDateTransformation
import woowacourse.payments.ui.addcard.util.PlaceholderTransformation
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.ExpireDateTextField
import woowacourse.payments.ui.component.OwnerNameTextField
import woowacourse.payments.ui.component.PasswordTextField
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(innerPadding: PaddingValues) {
    var cardInfo by rememberSaveable { mutableStateOf(CardInfoUiState()) }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(top = 14.dp)
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card()
        Column {
            CardNumberTextField(
                cardInfo = cardInfo,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
            )
            ExpireDateTextField(
                cardInfo = cardInfo,
                modifier = Modifier
                    .padding(top = 18.dp)
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
            AddCardScreen(it)
        }
    }
}