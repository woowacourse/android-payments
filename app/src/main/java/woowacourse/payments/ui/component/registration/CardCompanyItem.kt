package woowacourse.payments.ui.component.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray

@Composable
fun CardCompanyItem(
    companyLogo: Painter,
    companyName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Image(
            painter = companyLogo,
            contentDescription = stringResource(R.string.card_company_item_logo_description),
            modifier =
                Modifier.padding(top = 2.dp, bottom = 8.dp),
        )

        Text(
            text = companyName,
            fontSize = 16.sp,
            color = Gray,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanyItemPreview() {
    CardCompanyItem(
        companyLogo = painterResource(R.drawable.ic_kakao_symbol),
        companyName = "카카오 뱅크",
    )
}
