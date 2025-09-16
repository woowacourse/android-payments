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

class CardCompanyUiModel(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val color: Color,
) {
    companion object {
        fun from(cardCompany: CardCompany): CardCompanyUiModel =
            when (cardCompany) {
                CardCompany.BC -> CardCompanyUiModel(R.string.card_bc, R.drawable.bc, BC)
                CardCompany.SHINHAN ->
                    CardCompanyUiModel(
                        R.string.card_shinhan,
                        R.drawable.shinhan,
                        SHINHAN,
                    )

                CardCompany.KAKAO ->
                    CardCompanyUiModel(
                        R.string.card_kakaobank,
                        R.drawable.kakaobank,
                        KAKAO,
                    )

                CardCompany.HYUNDAI ->
                    CardCompanyUiModel(
                        R.string.card_hyundai,
                        R.drawable.hyundai,
                        HYUNDAI,
                    )

                CardCompany.WOORI ->
                    CardCompanyUiModel(
                        R.string.card_woori,
                        R.drawable.woori,
                        WOORI,
                    )

                CardCompany.LOTTE ->
                    CardCompanyUiModel(
                        R.string.card_lotte,
                        R.drawable.lotte,
                        LOTTE,
                    )

                CardCompany.HANA -> CardCompanyUiModel(R.string.card_hana, R.drawable.hana, HANA)
                CardCompany.KB -> CardCompanyUiModel(R.string.card_kb, R.drawable.kb, KB)
                CardCompany.NONE ->
                    CardCompanyUiModel(
                        R.string.card_default,
                        R.drawable.kb,
                        DEFAULT,
                    )
            }
    }
}
