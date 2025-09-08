package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun CardInfo(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = card.formatCardNumber(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp,
            lineHeight = 12.sp,
            maxLines = 1,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = card.ownerName,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
            )
            Text(
                text = card.formatExpiredDate(),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 12.sp,
            )
        }
    }
}

@Preview
@Composable
private fun CardInfoPreview() {
    CardInfo(
        card = CardUiModel(
            "1234567812345678",
            "0925",
            "INHYEOP LEE",
        )
    )
}
