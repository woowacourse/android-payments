package woowacourse.payments.ui.core

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import woowacourse.payments.ui.theme.Black33
import woowacourse.payments.ui.theme.GrayE5

enum class CardType(
    val parentAlignment: Alignment,
    val backgroundColor: Color,
) {
    EMPTY(Alignment.Companion.Center, GrayE5),
    PENDING(Alignment.Companion.CenterStart, Black33),
    REGISTERED(Alignment.Companion.CenterStart, Black33),
}
