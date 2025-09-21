package woowacourse.payments.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey52
import woowacourse.payments.ui.theme.KakaoYellow
import woowacourse.payments.ui.theme.ShinhanBlue

@Composable
fun CardCompanyItem(
    bank: CardCompanyUiModel,
    onClick: (CardCompanyUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .testTag("BankItem")
                .clickable { onClick(bank) }
                .width(78.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(bank.image),
            contentDescription = stringResource(bank.label),
            modifier = Modifier.size(37.dp),
            contentScale = ContentScale.Fit,
        )
        Spacer(Modifier.height(9.dp))
        Text(
            text = stringResource(bank.label),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Grey52,
        )
    }
}

@Preview(name = "BankItem_Shinhan", showBackground = true)
@Composable
private fun BankItemPreview_Shinhan() {
    AndroidpaymentsTheme {
        CardCompanyItem(
            bank =
                CardCompanyUiModel(
                    type = CardCompanyType.SHINHAN,
                    image = R.drawable.ic_bank_shinhan,
                    label = R.string.bank_shinhan,
                    background = ShinhanBlue.toArgb(),
                ),
            onClick = {},
        )
    }
}

@Preview(name = "BankItem_Kakao", showBackground = true)
@Composable
private fun BankItemPreview_Kakao() {
    AndroidpaymentsTheme {
        CardCompanyItem(
            bank =
                CardCompanyUiModel(
                    type = CardCompanyType.KAKAO,
                    image = R.drawable.ic_bank_kakao,
                    label = R.string.bank_kakao,
                    background = KakaoYellow.toArgb(),
                ),
            onClick = {},
        )
    }
}
