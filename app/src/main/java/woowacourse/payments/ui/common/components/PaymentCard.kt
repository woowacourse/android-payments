package woowacourse.payments.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.common.model.CardUiModel

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel? = null,
) {
    val cardCompanyDescription = stringResource(R.string.card_company_description)
    val cardNumberDescription = stringResource(R.string.card_number_description)
    val cardHolderNameDescription = stringResource(R.string.card_holder_name_description)
    val cardExpirationDateDescription = stringResource(R.string.card_expiration_date_description)

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(card?.color ?: 0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(horizontal = 12.dp),
    ) {
        Column {
            Text(
                text = card?.companyName ?: "",
                fontSize = 12.sp,
                color = Color.White,
                letterSpacing = 2.sp,
                modifier = Modifier.semantics { contentDescription = cardCompanyDescription },
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = card?.number?.toMaskedString() ?: "",
                fontSize = 12.sp,
                letterSpacing = 2.sp,
                color = Color.White,
                modifier = Modifier.semantics { contentDescription = cardNumberDescription },
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card?.holderName ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                    modifier =
                        Modifier.semantics { contentDescription = cardHolderNameDescription },
                )
                Text(
                    text = card?.expirationDate?.toDisplayString() ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                    modifier =
                        Modifier.semantics { contentDescription = cardExpirationDateDescription },
                )
            }
        }
    }
}

private fun String.toMaskedString(): String =
    this
        .chunked(4)
        .mapIndexed { index, chunk -> if (index < 2) chunk else "****" }
        .joinToString(" - ")

private fun String.toDisplayString(): String =
    this
        .chunked(2)
        .joinToString(" / ")

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewParameterProvider::class) card: CardUiModel?,
) {
    PaymentCard(card = card)
}

private class PaymentCardPreviewParameterProvider : PreviewParameterProvider<CardUiModel?> {
    private val card: CardUiModel =
        CardUiModel(
            companyName = "BC카드",
            color = 0xFFF04651,
            number = "1111222233334444",
            expirationDate = "0925",
            holderName = "CREW",
        )

    override val values: Sequence<CardUiModel?> =
        sequenceOf(
            null,
            card,
        )
}
