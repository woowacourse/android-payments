package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardUiModel.Companion.UNASSIGNED_ID
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    card: CardUiModel = CardUiModel.EMPTY,
) {
    val cardCompany = card.cardCompany
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .clickable(onClick = onClick)
                .background(
                    color = Color(cardCompany.background),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(14.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                fontWeight = FontWeight.W500,
                text = stringResource(id = cardCompany.label),
                color = Color.White,
                lineHeight = 12.sp,
                fontSize = 12.sp,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.W500,
                text = card.cardNumberMasked,
                color = Color.White,
                lineHeight = 12.sp,
                fontSize = 12.sp,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    lineHeight = 10.sp,
                    text = card.userName.orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp,
                )

                Spacer(Modifier.height(2.dp))
                Text(
                    lineHeight = 10.sp,
                    text = card.expirationFormatted,
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(
    name = "BC Card",
    showBackground = true,
)
@Composable
private fun PaymentCardPreview_BC() {
    AndroidpaymentsTheme {
        val sampleCard =
            CardUiModel(
                id = UNASSIGNED_ID,
                cardNumberRaw = "1111222233334444",
                expirationDateRaw = "0421",
                userName = "KIMGAHYUN",
                password = "1234",
                cardCompany = CardCompanyType.BC.toUiModel(),
            )
        PaymentCard(
            modifier = Modifier,
            card = sampleCard,
        )
    }
}

@Preview(
    name = "KB Card",
    showBackground = true,
)
@Composable
private fun PaymentCardPreview_Kb() {
    AndroidpaymentsTheme {
        val sampleCard =
            CardUiModel(
                id = UNASSIGNED_ID,
                cardCompany = CardCompanyType.KB.toUiModel(),
                cardNumberRaw = "1111222233334444",
                expirationDateRaw = "0421",
                userName = "KIMGAHYUN",
                password = "1234",
            )
        PaymentCard(
            modifier = Modifier,
            card = sampleCard,
        )
    }
}
