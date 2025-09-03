package woowacourse.payments.ui.newcard.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.GrayBackground
import woowacourse.payments.designsystem.theme.Yellow

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int? = null,
) {
    if (imageRes != null) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(width = 208.dp, height = 124.dp),
        )
    } else {
        EmptyCard(modifier)
    }
}

@Composable
private fun EmptyCard(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = GrayBackground,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Yellow,
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
    }
}

@Preview
@Composable
fun PaymentCardPreview() {
    PaymentCard()
}
