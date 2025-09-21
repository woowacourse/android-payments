package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Grey100
import woowacourse.payments.ui.theme.Yellow80

@Composable
fun RegisteredCard(
    cardUiModel: CardUiModel,
    modifier: Modifier = Modifier,
) {
    val cardColor = cardUiModel.cardCompanyUiModel?.color ?: Grey100

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = cardColor,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = cardUiModel.cardCompanyUiModel?.companyName ?: 0),
                color = Color.White,
                fontSize = 12.sp,
                lineHeight = 1.em,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(start = 14.dp, bottom = 12.dp),
            )

            Box(
                modifier =
                    Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Yellow80,
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            Text(
                text = cardUiModel.maskedCardNumber(),
                color = Color.White,
                lineHeight = 1.em,
                modifier = Modifier.padding(start = 12.dp),
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = cardUiModel.cardHolderName,
                    color = Color.White,
                    lineHeight = 1.em,
                    modifier = Modifier.padding(start = 12.dp),
                )
                Text(
                    text = cardUiModel.formattedExpiryDate(),
                    color = Color.White,
                    lineHeight = 1.em,
                    modifier =
                        Modifier
                            .padding(end = 12.dp)
                            .padding(top = 3.dp),
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisteredCardPreview() {
    RegisteredCard(
        cardUiModel =
            CardUiModel(
                cardNumber = "1234567890123456",
                cardHolderName = "홍길동",
                cardExpiryDate = "1224",
                cardPassword = "1234",
                cardCompanyUiModel = CardCompanyUiModel.SHINHAN,
            ),
    )
}
