package woowacourse.payments.view.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.view.BankType
import woowacourse.payments.view.CardUiModel
import woowacourse.payments.view.ui.theme.CardIcChip

private const val CARD_NUMBER_MASK = "*"
private const val CARD_NUMBER_GROUP_SEPARATOR = " - "
private const val EXPIRED_DATE_SEPARATOR = " / "

@Composable
fun PaymentCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    number: String? = null,
    owner: String? = null,
    expiredDate: String? = null,
    bankType: BankType? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    color = colorResource(bankType?.colorRes ?: R.color.card_not_selected),
                ).clickable(onClick = onClick)
                .testTag("PaymentCard"),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if (bankType?.cardNameRes == null) "" else stringResource(bankType.cardNameRes),
                modifier =
                    Modifier
                        .padding(start = 14.dp, bottom = 10.dp)
                        .testTag("PaymentCardBankNameText"),
                fontSize = 12.sp,
                color = Color.White,
            )
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
            PaymentCardDetail(
                number = number ?: "",
                owner = owner ?: "",
                expiredDate = expiredDate ?: "",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
            )
        }
    }
}

@Composable
fun PaymentCard(
    card: CardUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    PaymentCard(
        onClick = onClick,
        modifier = modifier,
        number = card.number,
        owner = card.holder,
        expiredDate = card.expiredDate,
        bankType = card.bankType,
    )
}

@Composable
private fun PaymentCardDetail(
    number: String,
    owner: String,
    expiredDate: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = number.markedCardNumber,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
            letterSpacing = 0.17.em,
            color = Color.White,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.W500,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = owner,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
            )
            Text(
                text =
                    expiredDate
                        .mapIndexed { index: Int, char: Char ->
                            if (index == 1) {
                                char + EXPIRED_DATE_SEPARATOR
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

private val String.markedCardNumber: String
    get() =
        chunked(4)
            .mapIndexed { index, string ->
                if (index > 1) {
                    string.map { CARD_NUMBER_MASK }.joinToString(separator = "")
                } else {
                    string
                }
            }.joinToString(separator = CARD_NUMBER_GROUP_SEPARATOR)

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentCardPreviewParameterProvider::class) card: CardUiModel?,
) {
    PaymentCard(
        onClick = {},
        number = card?.number,
        owner = card?.holder,
        expiredDate = card?.expiredDate,
        bankType = card?.bankType,
    )
}

private class PaymentCardPreviewParameterProvider :
    CollectionPreviewParameterProvider<CardUiModel?>(
        listOf(
            null,
            CardUiModel(
                number = "1234".repeat(4),
                holder = "CREW",
                expiredDate = "0421",
                bankType = BankType.BC,
            ),
        ),
    )

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PaymentCardDetailPreview() {
    PaymentCardDetail(
        number = "1234".repeat(4),
        owner = "CREW",
        expiredDate = "0421",
    )
}
