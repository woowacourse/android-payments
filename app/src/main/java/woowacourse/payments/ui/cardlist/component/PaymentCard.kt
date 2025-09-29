package woowacourse.payments.ui.cardlist.component

import android.R.attr.letterSpacing
import android.R.attr.lineHeight
import android.R.attr.onClick
import woowacourse.payments.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Black
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun PaymentCard(
    onEditCard: (CardUiModel) -> Unit = {},
    cardUiModel: CardUiModel,
    modifier: Modifier = Modifier,
) {
    val companyName = when (cardUiModel.cardCompanyUiModel) {
        is CardCompanyUiModel.SelectCardCompany -> {
            stringResource(cardUiModel.cardCompanyUiModel.displayName)
        }

        is CardCompanyUiModel.Default -> null
    }

    Box(
        modifier = modifier
            .padding(bottom = 32.dp)
            .size(width = 208.dp, height = 124.dp)
            .shadow(8.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                color = colorResource(
                    when (cardUiModel.cardCompanyUiModel) {
                        is CardCompanyUiModel.SelectCardCompany -> cardUiModel.cardCompanyUiModel.color
                        is CardCompanyUiModel.Default -> R.color.card
                    },
                )
            )
            .clickable(onClick = { onEditCard(cardUiModel) })
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
            text = companyName ?: "",
            color = Color.White
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 32.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = if (cardUiModel.number.length == 16) "${cardUiModel.number.take(4)} - ${
                cardUiModel.number.drop(4).take(4)
            } - **** - ****" else "",
            color = Color.White,
            letterSpacing = 0.17.em,
            lineHeight = 12.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = "${cardUiModel.ownerName}",
            color = Color.White,
            letterSpacing = 0.17.em,
            lineHeight = 12.sp
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 14.dp, bottom = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            text = "${cardUiModel.expiredDate}",
            color = Color.White,
            letterSpacing = 0.17.em,
            lineHeight = 12.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    val card = CardUiModel(
        number = "1234567890123456",
        ownerName = "Hwang Chaewon",
        expiredDate = "0230",
        password = "1234",
        cardCompanyUiModel = CardCompanyUiModel.Default,
    )
    PaymentCard({}, card)
}