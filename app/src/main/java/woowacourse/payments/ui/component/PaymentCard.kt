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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.Card
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
                ),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = if (detail != null) 10.dp else 0.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
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
                            .padding(horizontal = 14.dp),
                )
            }
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
            text = detail.markedCardNumber,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
            letterSpacing = 0.17.em,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W500,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = detail.owner,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
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
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
        }
    }
}

private val Card.markedCardNumber: String
    get() =
        number
            .chunked(4)
            .mapIndexed { index, string ->
                if (index > 1) {
                    string.map { "*" }.joinToString("")
                } else {
                    string
                }
            }.joinToString(separator = " - ")

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewParameterProvider::class) card: Card?,
) {
    PaymentCard(
        detail = card,
    )
}

private class PaymentCardPreviewParameterProvider :
    CollectionPreviewParameterProvider<Card?>(
        listOf(
            null,
            Card(
                number = "1234".repeat(4),
                owner = "CREW",
                expiredDate = "0421",
            ),
        ),
    )

@Preview(showBackground = true, backgroundColor = 0xFF333333)
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
