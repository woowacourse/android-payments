package woowacourse.payments.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.theme.BcRed
import woowacourse.payments.ui.theme.CardDefault
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

fun BankType.toUiModel(): BankUiModel =
    when (this) {
        BankType.BC ->
            BankUiModel(
                this,
                R.drawable.ic_bank_bc,
                R.string.bank_bc,
                BcRed,
            )

        BankType.SHINHAN ->
            BankUiModel(
                this,
                R.drawable.ic_bank_shinhan,
                R.string.bank_shinhan,
                ShinhanBlue,
            )

        BankType.KAKAO ->
            BankUiModel(
                this,
                R.drawable.ic_bank_kakao,
                R.string.bank_kakao,
                KakaoYellow,
            )

        BankType.HYUNDAI ->
            BankUiModel(
                this,
                R.drawable.ic_bank_hyundai,
                R.string.bank_hyundai,
                HyundaiBlack,
            )

        BankType.WOORI ->
            BankUiModel(
                this,
                R.drawable.ic_bank_woori,
                R.string.bank_woori,
                WooriBlue,
            )

        BankType.LOTTE ->
            BankUiModel(
                this,
                R.drawable.ic_bank_lotte,
                R.string.bank_lotte,
                LotteRed,
            )

        BankType.HANA ->
            BankUiModel(
                this,
                R.drawable.ic_bank_hana,
                R.string.bank_hana,
                HanaGreen,
            )

        BankType.KB ->
            BankUiModel(
                this,
                R.drawable.ic_bank_kb,
                R.string.bank_kb,
                KbBrown,
            )

        BankType.NOT_SELECTED ->
            BankUiModel(
                this,
                R.drawable.ic_bank_placeholder,
                R.string.bank_not_selected,
                CardDefault,
            )
    }
