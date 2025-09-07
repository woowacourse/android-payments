package woowacourse.payments.ui

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.CardBlack
import woowacourse.payments.ui.theme.CardIcChip

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    detail: Card? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = CardBlack,
                    shape = RoundedCornerShape(5.dp),
                ).padding(
                    start = 14.dp,
                    end = 14.dp,
                    bottom = 10.dp,
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = CardIcChip,
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
        if (detail != null) {
            PaymentCardDetail(
                detail = detail,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun PaymentCardDetail(
    detail: Card,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = detail.number.toMarkedCardNumber(),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = detail.owner,
                color = Color.White,
            )
            Text(
                text =
                    detail.expiredDate
                        .mapIndexed { index: Int, char: Char ->
                            if (index == 1) {
                                "$char / "
                            } else {
                                char
                            }
                        }.joinToString(separator = ""),
                color = Color.White,
            )
        }
    }
}

private fun String.toMarkedCardNumber(): String =
    chunked(4)
        .mapIndexed { index, string ->
            if (index > 1) {
                string.map { "*" }.joinToString("")
            } else {
                string
            }
        }.joinToString(separator = " - ")

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        detail =
            Card(
                number = "1234".repeat(4),
                owner = "CREW",
                expiredDate = "0421",
            ),
    )
}

@Preview
@Composable
private fun PaymentCardDetailPreview() {
    PaymentCardDetail(
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
    )
}
