package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@Composable
fun Card() {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .height(124.dp)
            .shadow(8.dp)
            .width(208.dp)
            .background(
                color = colorResource(id = R.color.payments_card_background),
                shape = RoundedCornerShape(5.dp),)

    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .shadow(8.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = colorResource(id = R.color.payments_card_chip),
                    shape = RoundedCornerShape(5.dp),
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    Card()
}