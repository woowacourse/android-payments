package woowacourse.payments.ui.newcard.uiModel

import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Blue
import woowacourse.payments.ui.theme.Brown
import woowacourse.payments.ui.theme.Green
import woowacourse.payments.ui.theme.LotteRed
import woowacourse.payments.ui.theme.Red
import woowacourse.payments.ui.theme.Yellow
import woowacourse.payments.ui.theme.shinhanBlue

enum class BankTypeUiModel(
    val displayName: String,
    val color: Color,
    val logo: Int?,
) {
    BC("BC 카드", Red, R.drawable.symbol_mark_bc),
    SHINHAN("신한 카드", shinhanBlue, R.drawable.symbol_mark_shinhan),
    KAKAO("카카오뱅크", Yellow, R.drawable.symbol_mark_kakao),
    HYUNDAI("현대 카드", Black, R.drawable.symbol_mark_hyundae),
    WOORI("우리 카드", Blue, R.drawable.symbol_mark_woori),
    LOTTE("롯데 카드", LotteRed, R.drawable.symbol_mark_lotte),
    HANA("하나 카드", Green, R.drawable.symbol_mark_bc),
    KB("국민 카드", Brown, R.drawable.symbol_mark_kb),
    NOT_SELECTED("", Black, null),

}