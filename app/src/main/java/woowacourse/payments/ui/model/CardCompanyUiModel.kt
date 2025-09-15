package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany

@Parcelize
data class CardCompanyUiModel(
    val name: String,
    @DrawableRes val logo: Int,
    val cardColor: Long,
) : Parcelable

fun CardCompanyUiModel.toCardCompanyOrNull(): CardCompany? =
    CardCompany.entries.find { cardCompany: CardCompany ->
        cardCompany.companyName == name
    }

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.BC_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_bc_card,
                0xFFF04651,
            )

        CardCompany.SHINHAN_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_shinhan_card,
                0xFF0046FF,
            )

        CardCompany.KAKAO_BANK ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_kakao_bank,
                0xFFFFE600,
            )

        CardCompany.HYUNDAI_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_hyundai_card,
                0xFF000000,
            )

        CardCompany.WOORI_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_woori_card,
                0xFF027BC8,
            )

        CardCompany.LOTTE_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_lotte_card,
                0xFFED1C25,
            )

        CardCompany.HANA_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_hana_card,
                0xFF019490,
            )

        CardCompany.KB_CARD ->
            CardCompanyUiModel(
                companyName,
                R.drawable.icon_kb_card,
                0xFF554E45,
            )
    }
