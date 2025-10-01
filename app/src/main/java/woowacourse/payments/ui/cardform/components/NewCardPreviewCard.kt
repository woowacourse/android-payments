package woowacourse.payments.ui.cardform.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.designsystem.theme.Yellow
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.common.components.BankLabel
import woowacourse.payments.ui.common.mapper.toColor

@Composable
fun NewCardPreviewCard(
    bankType: BankType,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = bankType.toColor(),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(color = Yellow, shape = RoundedCornerShape(4.dp)),
        )
        BankLabel(
            bankType = bankType,
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 14.dp, top = 12.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardPreviewCardPreview() {
    NewCardPreviewCard(bankType = BankType.HYUNDAI)
}
