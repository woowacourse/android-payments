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
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardCompanyUiModel

@Composable
fun CardCompanyButton(cardCompany: CardCompanyUiModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(cardCompany.logo),
            contentDescription = "",
            modifier = Modifier.size(37.dp),
        )
        Spacer(Modifier.height(9.dp))
        Text(cardCompany.name)
    }
}

@Preview(showBackground = true, name = "BC카드")
@Composable
fun CardCompanyBoxPreview() {
    CardCompanyButton(
        CardCompanyUiModel(
            "BC카드",
            R.drawable.icon_bc_card,
        ),
    )
}
