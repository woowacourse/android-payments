package woowacourse.payments.ui.addcard

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import woowacourse.payments.domain.BankType

@Composable
fun BankImage(
    bank: BankType,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(bank.bankLogo),
        contentDescription = "${stringResource(id = bank.bankName)} 로고",
    )
}
