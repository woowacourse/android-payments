package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.newcard.uiModel.CardCompanyUiModel

@Composable
fun CardType(
    cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.Default(),
    modifier: Modifier = Modifier,
) {
    val companyName = when (cardCompanyUiModel) {
        is CardCompanyUiModel.SelectCardCompany -> {
            stringResource(cardCompanyUiModel.displayName)
        }

        is CardCompanyUiModel.Default -> stringResource(cardCompanyUiModel.displayName)
    }

    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = when (cardCompanyUiModel) {
                    is CardCompanyUiModel.SelectCardCompany -> cardCompanyUiModel.color
                    is CardCompanyUiModel.Default -> cardCompanyUiModel.color
                },
            )
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart)
        )

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 14.dp, top = 15.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = companyName,
            color = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BankTypeCardPreview() {
    CardType()
}