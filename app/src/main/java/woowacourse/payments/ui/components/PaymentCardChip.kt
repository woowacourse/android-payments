package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.YellowCB

@Composable
fun PaymentCardChip(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .size(width = 40.dp, height = 26.dp)
                .padding(start = 2.dp)
                .background(
                    color = YellowCB,
                    shape = RoundedCornerShape(4.dp),
                ),
    )
}

@Preview
@Composable
fun PaymentCardChipPreview() {
    PaymentCardChip()
}
