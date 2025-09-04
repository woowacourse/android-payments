package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.newcard.components.CardNumber
import woowacourse.payments.ui.newcard.components.ExpirationDate
import woowacourse.payments.ui.newcard.components.Name
import woowacourse.payments.ui.newcard.components.Password
import woowacourse.payments.ui.newcard.components.PaymentCard

@Composable
fun NewCardScreen(innerPadding: PaddingValues) {
    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
    ) {
        PaymentCard(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 14.dp),
        )
        CardNumber(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 24.dp, end = 24.dp),
            label = stringResource(R.string.main_card_number_label),
            placeholder = stringResource(R.string.main_card_number_placeholder),
        )
        ExpirationDate(
            modifier =
                Modifier
                    .padding(start = 24.dp, top = 30.dp),
            label = stringResource(R.string.main_expiration_date_label),
            placeholder = stringResource(R.string.main_expiration_date_placeholder),
        )
        Name(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 30.dp, end = 24.dp),
            label = stringResource(R.string.main_name_label),
            placeholder = stringResource(R.string.main_name_placeholder),
        )
        Password(
            modifier =
                Modifier
                    .padding(start = 24.dp, top = 30.dp),
            label = stringResource(R.string.main_password_label),
            placeholder = stringResource(R.string.main_password_placeholder),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    NewCardScreen(innerPadding = PaddingValues())
}
