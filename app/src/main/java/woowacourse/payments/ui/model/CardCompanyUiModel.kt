package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.theme.BcRed
import woowacourse.payments.ui.theme.CardDefault
import woowacourse.payments.ui.theme.HanaGreen
import woowacourse.payments.ui.theme.HyundaiBlack
import woowacourse.payments.ui.theme.KakaoYellow
import woowacourse.payments.ui.theme.KbBrown
import woowacourse.payments.ui.theme.LotteRed
import woowacourse.payments.ui.theme.ShinhanBlue
import woowacourse.payments.ui.theme.WooriBlue

@Parcelize
data class CardCompanyUiModel(
    val type: CardCompanyType,
    @DrawableRes val image: Int,
    @StringRes val label: Int,
    @ColorInt val background: Int,
) : Parcelable

fun CardCompanyType.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompanyType.BC ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_bc,
                R.string.bank_bc,
                BcRed,
            )

        CardCompanyType.SHINHAN ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_shinhan,
                R.string.bank_shinhan,
                ShinhanBlue,
            )

        CardCompanyType.KAKAO ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_kakao,
                R.string.bank_kakao,
                KakaoYellow,
            )

        CardCompanyType.HYUNDAI ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_hyundai,
                R.string.bank_hyundai,
                HyundaiBlack,
            )

        CardCompanyType.WOORI ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_woori,
                R.string.bank_woori,
                WooriBlue,
            )

        CardCompanyType.LOTTE ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_lotte,
                R.string.bank_lotte,
                LotteRed,
            )

        CardCompanyType.HANA ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_hana,
                R.string.bank_hana,
                HanaGreen,
            )

        CardCompanyType.KB ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_kb,
                R.string.bank_kb,
                KbBrown,
            )

        CardCompanyType.NOT_SELECTED ->
            CardCompanyUiModel(
                this,
                R.drawable.ic_bank_placeholder,
                R.string.bank_not_selected,
                CardDefault,
            )
    }
