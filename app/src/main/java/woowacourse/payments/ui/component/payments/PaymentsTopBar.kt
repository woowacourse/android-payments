package woowacourse.payments.ui.component.payments

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.screen.PaymentCardCount
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Black1D


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsTopBar(
    paymentCardCount: PaymentCardCount = PaymentCardCount.Empty,
    onAddNewCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.payments_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.W400,
                color = Black1D
            )
        },
        actions = {
            if (paymentCardCount == PaymentCardCount.MoreThanOne) {
                TextButton(onClick = {
                    onAddNewCardClick()
                }) {
                    Text(text = stringResource(R.string.payments_top_bar_icon_name), fontSize = 18.sp, fontWeight = FontWeight.W700, color = Black)
                }
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun PaymentsTopBarPreview() {
    PaymentsTopBar(onAddNewCardClick = {})
}