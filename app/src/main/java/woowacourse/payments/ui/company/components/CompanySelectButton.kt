package woowacourse.payments.ui.company.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun CompanySelectButton(
    company: CompanyUiModel,
    onClick: (CompanyUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.clickable { onClick(company) },
    ) {
        Image(
            painter = painterResource(id = company.logo),
            contentDescription = null,
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
        )
        Text(
            text = company.name,
            fontSize = 16.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(80.dp),
        )
    }
}

@Preview
@Composable
private fun CompanySelectButtonPreview() {
    CompanySelectButton(
        company = CardCompany.KAKAO.toUiModel(),
        onClick = {},
    )
}
