package woowacourse.payments.ui.common.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.domain.model.BankType.BC
import woowacourse.payments.domain.model.BankType.HANA
import woowacourse.payments.domain.model.BankType.HYUNDAI
import woowacourse.payments.domain.model.BankType.KAKAO_BANK
import woowacourse.payments.domain.model.BankType.KB
import woowacourse.payments.domain.model.BankType.LOTTE
import woowacourse.payments.domain.model.BankType.NOT_SELECTED
import woowacourse.payments.domain.model.BankType.SHINHAN
import woowacourse.payments.domain.model.BankType.WOORI

@StringRes
fun BankType.toNameRes(): Int =
    when (this) {
        NOT_SELECTED -> R.string.bank_not_selected
        BC -> R.string.bank_bc
        SHINHAN -> R.string.bank_shinhan
        KAKAO_BANK -> R.string.bank_kakaobank
        HYUNDAI -> R.string.bank_hyundai
        WOORI -> R.string.bank_woori
        LOTTE -> R.string.bank_lotte
        HANA -> R.string.bank_hana
        KB -> R.string.bank_kb
    }

@DrawableRes
fun BankType.toIconRes(): Int =
    when (this) {
        NOT_SELECTED -> 0
        BC -> R.drawable.ic_bc_card
        SHINHAN -> R.drawable.ic_shinhan_card
        KAKAO_BANK -> R.drawable.ic_kakao_bank
        HYUNDAI -> R.drawable.ic_hyundai_card
        WOORI -> R.drawable.ic_woori_card
        LOTTE -> R.drawable.ic_lotte_card
        HANA -> R.drawable.ic_hana_card
        KB -> R.drawable.ic_kb_card
    }

fun BankType.toColor(): Color =
    when (this) {
        NOT_SELECTED -> Color(0xFF333333)
        BC -> Color(0xFFF04651)
        SHINHAN -> Color(0xFF0046FF)
        KAKAO_BANK -> Color(0xFFFFE600)
        HYUNDAI -> Color(0xFF000000)
        WOORI -> Color(0xFF007BC8)
        LOTTE -> Color(0xFFED1C24)
        HANA -> Color(0xFF009490)
        KB -> Color(0xFF6A6156)
    }
