package woowacourse.payments.domain

import androidx.compose.ui.graphics.Color
import woowacourse.payments.domain.BankType.*
import woowacourse.payments.ui.theme.BC_CARD_COLOR
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
) {
    companion object {
        fun fromName(name: String): Bank? =
            when (name) {
                "BC카드" -> Bank(BC, "BC카드")
                "신한카드" -> Bank(SHINHAN, "신한카드")
                "카카오뱅크" -> Bank(KAKAO, "카카오뱅크")
                "현대카드" -> Bank(HYUNDAI, "현대카드")
                "우리카드" -> Bank(WOORI, "우리카드")
                "롯데카드" -> Bank(LOTTE, "롯데카드")
                "하나카드" -> Bank(HANA, "하나카드")
                "국민카드" -> Bank(KB, "국민카드")
                else -> null
            }
    }
}

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
    }
