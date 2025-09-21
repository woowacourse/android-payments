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
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.BankViewType
import woowacourse.payments.ui.theme.Black33
import woowacourse.payments.ui.theme.Yellow64
import java.time.format.DateTimeFormatter

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
    bankViewType: BankViewType,
) {
    Box(
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = bankViewType.color ?: Black33,
                    shape = RoundedCornerShape(5.dp),
                ).padding(16.dp),
    ) {
        Text(
            modifier = Modifier.align(alignment = Alignment.TopStart),
            text = bankViewType.nameRes?.let { stringResource(it) } ?: "",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Box(
            modifier =
                Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Yellow64,
                        shape = RoundedCornerShape(4.dp),
                    ).align(Alignment.CenterStart),
        )

        if (card != null) {
            CardInfo(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 0.dp, top = 30.dp),
                card = card,
            )
        }
    }
}

@Composable
private fun CardInfo(
    modifier: Modifier = Modifier,
    card: Card,
) {
    Column(modifier = modifier.padding(top = 8.dp)) {
        Text(
            text = card.cardNumber.formatCardNumber(),
            lineHeight = 12.sp,
            color = Color.White,
            fontSize = 12.sp,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = card.cardOwner ?: "",
                fontSize = 12.sp,
                lineHeight = 12.sp,
                color = Color.White,
            )
            Text(
                text = card.expiryDate.format(DateTimeFormatter.ofPattern("MM/yy")),
                lineHeight = 12.sp,
                fontSize = 12.sp,
                color = Color.White,
            )
        }
    }
}

fun String.formatCardNumber(): String =
    chunked(4)
        .mapIndexed { index, s ->
            if (index < 2) s else "****"
        }.joinToString(" - ")

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(bankViewType = BankViewType.BC)
}
