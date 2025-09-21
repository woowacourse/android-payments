package woowacourse.payments.ui.component

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.Grey100
import woowacourse.payments.ui.theme.Yellow80

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardCompany: CardCompanyUiModel? = null,
    onCompanyClick: (() -> Unit)? = null,
) {
    val cardColor = cardCompany?.color ?: Grey100
    val clickableModifier =
        if (onCompanyClick != null) {
            Modifier.clickable { onCompanyClick() }
        } else {
            Modifier
        }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .then(clickableModifier)
                .background(
                    color = cardColor,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        cardCompany?.let { company ->
            Text(
                text = stringResource(id = company.companyName),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 14.dp, bottom = 85.dp),
            )
        }

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
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard()
}
