package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.YellowBB

@Composable
fun CardChip(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = YellowBB,
                    shape = RoundedCornerShape(4.dp),
                ),
    )
}

@Composable
@Preview(showBackground = true)
private fun CardChipPreview() {
    CardChip()
}
