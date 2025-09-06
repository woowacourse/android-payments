package woowacourse.payments.domain

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import woowacourse.payments.ui.theme.Black33
import woowacourse.payments.ui.theme.GrayE5

enum class CardType(
    val parentAlignment: Alignment,
    val backgroundColor: Color,
) {
    EMPTY(Alignment.Center, GrayE5),
    PENDING(Alignment.CenterStart, Black33),
    REGISTERED(Alignment.CenterStart, Black33)
}
