package woowacourse.payments.domain

import androidx.compose.ui.graphics.Color
import woowacourse.payments.domain.BankType.*
import woowacourse.payments.ui.theme.BC_CARD_COLOR
import woowacourse.payments.ui.theme.DEFAULT_CARD_COLOR
import woowacourse.payments.ui.theme.HANA_CARD_COLOR
import woowacourse.payments.ui.theme.HYUNDAI_CARD_COLOR
import woowacourse.payments.ui.theme.KAKAO_CARD_COLOR
import woowacourse.payments.ui.theme.KB_CARD_COLOR
import woowacourse.payments.ui.theme.LOTTE_CARD_COLOR
import woowacourse.payments.ui.theme.SHINHAN_CARD_COLOR
import woowacourse.payments.ui.theme.WOORI_CARD_COLOR

data class Bank(
    val bankType: BankType,
    val name: String,
)

fun Bank.color(): Color =
    when (this.bankType) {
        BC -> BC_CARD_COLOR
        SHINHAN -> SHINHAN_CARD_COLOR
        KAKAO -> KAKAO_CARD_COLOR
        HYUNDAI -> HYUNDAI_CARD_COLOR
        WOORI -> WOORI_CARD_COLOR
        LOTTE -> LOTTE_CARD_COLOR
        HANA -> HANA_CARD_COLOR
        KB -> KB_CARD_COLOR
        NOT_SELECTED -> DEFAULT_CARD_COLOR
    }
