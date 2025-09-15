package woowacourse.payments.ui.company.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.company.model.CompanyUiModel
import woowacourse.payments.ui.company.model.toUiModel

private const val COLUMN_COUNT = 4

@Composable
fun CompanySelectRow(
    companies: List<CardCompany>,
    onCompanySelected: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        companies.forEach { company: CardCompany ->
            CardCompanyItem(
                company = company,
                onClick = onCompanySelected,
            )
        }
    }
}

@Composable
private fun CardCompanyItem(
    company: CardCompany,
    onClick: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    val companyUiModel: CompanyUiModel = company.toUiModel()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.clickable { onClick(company) },
    ) {
        Image(
            painter = painterResource(id = companyUiModel.logo),
            contentDescription = null,
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
        )
        Text(
            text = companyUiModel.name,
            fontSize = 16.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CompanySelectRowPreview() {
    CompanySelectRow(
        companies = CardCompany.entries,
        onCompanySelected = {},
    )
}
