package woowacourse.payments.ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.BankBC
import woowacourse.payments.designsystem.theme.BankHana
import woowacourse.payments.designsystem.theme.BankHyundai
import woowacourse.payments.designsystem.theme.BankKB
import woowacourse.payments.designsystem.theme.BankKakaoBank
import woowacourse.payments.designsystem.theme.BankLotte
import woowacourse.payments.designsystem.theme.BankShinhan
import woowacourse.payments.designsystem.theme.BankWoori
import woowacourse.payments.designsystem.theme.GrayBackground
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
        NOT_SELECTED -> GrayBackground
        BC -> BankBC
        SHINHAN -> BankShinhan
        KAKAO_BANK -> BankKakaoBank
        HYUNDAI -> BankHyundai
        WOORI -> BankWoori
        LOTTE -> BankLotte
        HANA -> BankHana
        KB -> BankKB
    }
