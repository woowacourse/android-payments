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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun RegisteredCard(
    cardUiModel: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
            Text(
                text = cardUiModel.maskedCardNumber(),
                color = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = cardUiModel.cardHolderName,
                    color = Color.White,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Text(
                    text = cardUiModel.formattedExpiryDate(),
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .padding(top = 3.dp)
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun RegisteredCardPreview() {
    RegisteredCard(
        cardUiModel = CardUiModel(
            cardNumber = "1234567890123456",
            cardHolderName = "홍길동",
            cardExpiryDate = "1224",
            cardPassword = "1234"
        )
    )
}

