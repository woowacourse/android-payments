package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.preview.OneCardPreviewParameterProvider

@Composable
fun RegisteredCard(
    card: Card,
    numberGroupSize: Int,
    numberSeparator: String,
    numberMaskingChar: String,
    expireDateGroupSize: Int,
    expireDateSeparator: String,
    modifier: Modifier = Modifier,
) {
    val resourceProvider = CompanyResourceProvider()
    val companyName: String? =
        resourceProvider.getCompanyName(card.bank)?.let {
            stringResource(it)
        }

    Column(
        modifier =
            modifier
                .fillMaxWidth(),
    ) {
        NewCardName(companyName)

        CardChip()

        Text(
            text =
                formatCardNumber(
                    card.number,
                    numberGroupSize,
                    numberSeparator,
                    numberMaskingChar,
                ),
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 13.dp),
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp),
        ) {
            Text(
                text = card.ownerName,
                fontSize = 12.sp,
                color = Color.White,
            )

            Text(
                text =
                    formatExpireDate(
                        card.expireDate,
                        expireDateGroupSize,
                        expireDateSeparator,
                    ),
                fontSize = 12.sp,
                color = Color.White,
            )
        }
    }
}

private fun formatCardNumber(
    cardNumber: String,
    groupSize: Int,
    separator: String,
    cardMaskChar: String,
): String {
    val visibleLength = Card.CARD_NUMBER_MASKING_LENGTH
    val visiblePart = cardNumber.take(visibleLength)
    val maskedPart = cardMaskChar.repeat(cardNumber.length - visibleLength)
    return (visiblePart + maskedPart)
        .chunked(groupSize)
        .joinToString(separator)
}

private fun formatExpireDate(
    expireDate: String,
    groupSize: Int,
    separator: String,
): String = expireDate.chunked(groupSize).joinToString(separator)

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun RegisteredCardPreview(
    @PreviewParameter(OneCardPreviewParameterProvider::class) card: Card,
) {
    RegisteredCard(
        card,
        numberGroupSize = 4,
        numberSeparator = " - ",
        numberMaskingChar = "*",
        expireDateGroupSize = 2,
        expireDateSeparator = " / ",
    )
}
