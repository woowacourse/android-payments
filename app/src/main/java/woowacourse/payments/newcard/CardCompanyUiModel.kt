package woowacourse.payments.newcard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

class CardCompanyUiModel(
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
) {
    companion object {
        fun from(cardCompany: CardCompany): CardCompanyUiModel =
            when (cardCompany) {
                CardCompany.BC -> CardCompanyUiModel(R.string.card_bc, R.drawable.bc)
                CardCompany.SHINHAN -> CardCompanyUiModel(R.string.card_shinhan, R.drawable.shinhan)
                CardCompany.KAKAO ->
                    CardCompanyUiModel(
                        R.string.card_kakaobank,
                        R.drawable.kakaobank,
                    )

                CardCompany.HYUNDAI -> CardCompanyUiModel(R.string.card_hyundai, R.drawable.hyundai)
                CardCompany.WOORI -> CardCompanyUiModel(R.string.card_woori, R.drawable.woori)
                CardCompany.LOTTE -> CardCompanyUiModel(R.string.card_lotte, R.drawable.lotte)
                CardCompany.HANA -> CardCompanyUiModel(R.string.card_hana, R.drawable.hana)
                CardCompany.KB -> CardCompanyUiModel(R.string.card_kb, R.drawable.kb)
            }
    }
}
