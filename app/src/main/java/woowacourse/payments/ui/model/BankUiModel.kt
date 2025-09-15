package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.theme.BcRed
import woowacourse.payments.ui.theme.HanaGreen
import woowacourse.payments.ui.theme.HyundaiBlack
import woowacourse.payments.ui.theme.KakaoYellow
import woowacourse.payments.ui.theme.KbBrown
import woowacourse.payments.ui.theme.LotteRed
import woowacourse.payments.ui.theme.ShinhanBlue
import woowacourse.payments.ui.theme.WooriBlue

data class BankUiModel(
    val type: BankType,
    @DrawableRes val image: Int,
    @StringRes val label: Int,
    val background: Color,
)

object BankCatalog {
    val banks: List<BankUiModel> =
        listOf(
            BankUiModel(
                BankType.BC,
                R.drawable.ic_bank_bc,
                R.string.bank_bc,
                BcRed,
            ),
            BankUiModel(
                BankType.SHINHAN,
                R.drawable.ic_bank_shinhan,
                R.string.bank_shinhan,
                ShinhanBlue,
            ),
            BankUiModel(
                BankType.KAKAO,
                R.drawable.ic_bank_kakao,
                R.string.bank_kakao,
                KakaoYellow,
            ),
            BankUiModel(
                BankType.HYUNDAI,
                R.drawable.ic_bank_hyundai,
                R.string.bank_hyundai,
                HyundaiBlack,
            ),
            BankUiModel(
                BankType.WOORI,
                R.drawable.ic_bank_woori,
                R.string.bank_woori,
                WooriBlue,
            ),
            BankUiModel(
                BankType.LOTTE,
                R.drawable.ic_bank_lotte,
                R.string.bank_lotte,
                LotteRed,
            ),
            BankUiModel(
                BankType.HANA,
                R.drawable.ic_bank_hana,
                R.string.bank_hana,
                HanaGreen,
            ),
            BankUiModel(
                BankType.KB,
                R.drawable.ic_bank_kb,
                R.string.bank_kb,
                KbBrown,
            ),
        )

    val byType: Map<BankType, BankUiModel> = banks.associateBy { it.type }
}
