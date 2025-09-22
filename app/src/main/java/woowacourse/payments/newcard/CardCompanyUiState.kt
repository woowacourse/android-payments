package woowacourse.payments.newcard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.BC
import woowacourse.payments.ui.theme.DEFAULT
import woowacourse.payments.ui.theme.HANA
import woowacourse.payments.ui.theme.HYUNDAI
import woowacourse.payments.ui.theme.KAKAO
import woowacourse.payments.ui.theme.KB
import woowacourse.payments.ui.theme.LOTTE
import woowacourse.payments.ui.theme.SHINHAN
import woowacourse.payments.ui.theme.WOORI

data class CardCompanyUiState(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val color: Color,
) {
    fun toDomain(): CardCompany =
        when (nameResId) {
            R.string.card_bc -> CardCompany.BC
            R.string.card_shinhan -> CardCompany.SHINHAN
            R.string.card_kakaobank -> CardCompany.KAKAO
            R.string.card_hyundai -> CardCompany.HYUNDAI
            R.string.card_woori -> CardCompany.WOORI
            R.string.card_lotte -> CardCompany.LOTTE
            R.string.card_hana -> CardCompany.HANA
            R.string.card_kb -> CardCompany.KB
            else -> CardCompany.NONE
        }

    companion object {
        fun from(cardCompany: CardCompany): CardCompanyUiState =
            when (cardCompany) {
                CardCompany.BC -> CardCompanyUiState(R.string.card_bc, R.drawable.bc, BC)
                CardCompany.SHINHAN ->
                    CardCompanyUiState(
                        R.string.card_shinhan,
                        R.drawable.shinhan,
                        SHINHAN,
                    )

                CardCompany.KAKAO ->
                    CardCompanyUiState(
                        R.string.card_kakaobank,
                        R.drawable.kakaobank,
                        KAKAO,
                    )

                CardCompany.HYUNDAI ->
                    CardCompanyUiState(
                        R.string.card_hyundai,
                        R.drawable.hyundai,
                        HYUNDAI,
                    )

                CardCompany.WOORI ->
                    CardCompanyUiState(
                        R.string.card_woori,
                        R.drawable.woori,
                        WOORI,
                    )

                CardCompany.LOTTE ->
                    CardCompanyUiState(
                        R.string.card_lotte,
                        R.drawable.lotte,
                        LOTTE,
                    )

                CardCompany.HANA -> CardCompanyUiState(R.string.card_hana, R.drawable.hana, HANA)
                CardCompany.KB -> CardCompanyUiState(R.string.card_kb, R.drawable.kb, KB)
                CardCompany.NONE ->
                    CardCompanyUiState(
                        R.string.card_default,
                        R.drawable.kb,
                        DEFAULT,
                    )
            }
    }
}
