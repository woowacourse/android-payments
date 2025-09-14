package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.model.Company

@Composable
fun CompanyItem(
    company: Company,
    onClick: (Company) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier.clickable {
                onClick(company)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(37.dp),
            painter = painterResource(id = company.icon),
            contentDescription = stringResource(R.string.new_card_company_icon_description, company.name),
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = company.name,
            fontWeight = W500,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.085).em,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompanyItemPreview() {
    CompanyItem(
        company =
            Company(
                icon = R.drawable.ic_bc,
                name = "BC카드",
            ),
        onClick = {},
    )
}
