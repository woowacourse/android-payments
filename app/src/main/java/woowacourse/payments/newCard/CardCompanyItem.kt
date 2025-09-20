package woowacourse.payments.newCard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun CardCompanyItem(
    cardCompanyUiModel: CardCompanyUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(id = cardCompanyUiModel.iconRes),
            tint = Color.Unspecified,
            contentDescription = cardCompanyUiModel.displayName,
            modifier = Modifier.size(37.dp),
        )
        Text(
            text = cardCompanyUiModel.displayName,
            fontSize = 16.sp,
            color = Color(0xFF525252),
        )
    }
}

@Preview
@Composable
private fun CardCompanyItemPreview() {
    AndroidpaymentsTheme {
        CardCompanyItem(
            cardCompanyUiModel = CardCompanyUiModel(
                company = CardCompany.BC,
                iconRes = R.drawable.ic_bc,
                color = Color(0xFF121212)
            ),
        )
    }
}
