package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun CardInfo(
    card: CardUiModel,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = CardTextStyle,
) {
    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        Column(
            modifier = modifier,
        ) {
            Text(
                text = card.formatCardNumber(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = LocalTextStyle.current.copy(letterSpacing = 2.sp),
                maxLines = 1,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = card.formatOwnerName())
                Text(text = card.formatExpiredDate())
            }
        }
    }
}

private val CardTextStyle =
    TextStyle(
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 12.sp,
    )

@Preview
@Composable
private fun CardInfoPreview() {
    CardInfo(
        card =
            CardUiModel(
                "1234567812345678",
                "0925",
                "INHYEOP LEE",
            ),
    )
}
