package woowacourse.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.CardOwnerNameTextField
import woowacourse.payments.ui.component.ExpiredDateTextField
import woowacourse.payments.ui.component.PasswordTextField
import woowacourse.payments.ui.component.PaymentCard

@Composable
fun CardAdditionColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        PaymentCard(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp, bottom = 28.dp)
        )
        CardNumberTextField(
            modifier = Modifier
                .fillMaxWidth()

        )
        ExpiredDateTextField(
            modifier = Modifier
                .padding(top = 18.dp)
        )
        CardOwnerNameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
        )
        PasswordTextField()
    }
}

@Preview
@Composable
private fun CardAdditionColumnPreview() {
    CardAdditionColumn()
}