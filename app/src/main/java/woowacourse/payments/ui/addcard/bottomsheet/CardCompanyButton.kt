package woowacourse.payments.ui.addcard.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.toUiModel

@Composable
fun CardCompanyButton(
    company: CardCompanyUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(company.logo),
            contentDescription = "",
            modifier = Modifier.size(37.dp),
        )
        Spacer(Modifier.height(9.dp))
        Text(company.name)
    }
}

@Preview(showBackground = true, name = "BC카드")
@Composable
fun CardCompanyBoxPreview() {
    CardCompanyButton(CardCompany.BC_CARD.toUiModel())
}
