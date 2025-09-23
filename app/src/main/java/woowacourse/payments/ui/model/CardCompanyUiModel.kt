package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.Black

@Parcelize
sealed interface CardCompanyUiModel : Parcelable {
    data object Default : CardCompanyUiModel

    data class SelectCardCompany(
        @StringRes val displayName: Int,
        @ColorRes val color: Int,
        @DrawableRes val logo: Int,
        val company: CardCompany,
    ) : CardCompanyUiModel
}

fun CardCompany.toUiModel(): CardCompanyUiModel.SelectCardCompany = cardCompanyMap.getValue(this)
fun CardCompanyUiModel.toDomain(): CardCompany =
    when (this) {
        is CardCompanyUiModel.Default -> throw IllegalArgumentException()
        is CardCompanyUiModel.SelectCardCompany -> this.company
    }

val cardCompanyMap: Map<CardCompany, CardCompanyUiModel.SelectCardCompany> = mapOf(
    CardCompany.BC to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_bc,
        R.color.card_bc,
        R.drawable.symbol_mark_bc,
        CardCompany.BC
    ),
    CardCompany.SHINHAN to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_shinhan,
        R.color.card_shinhan,
        R.drawable.symbol_mark_shinhan,
        CardCompany.SHINHAN
    ),
    CardCompany.KAKAO to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kakao,
        R.color.card_kakao,
        R.drawable.symbol_mark_kakao,
        CardCompany.KAKAO
    ),
    CardCompany.HYUNDAE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hyundae,
        R.color.card_hyundai,
        R.drawable.symbol_mark_hyundae,
        CardCompany.HYUNDAE
    ),
    CardCompany.WOORI to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_woori,
        R.color.card_woori,
        R.drawable.symbol_mark_woori,
        CardCompany.WOORI
    ),
    CardCompany.LOTTE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_lotte,
        R.color.card_lotte,
        R.drawable.symbol_mark_lotte,
        CardCompany.LOTTE
    ),
    CardCompany.HANA to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hana,
        R.color.card_hana,
        R.drawable.symbol_mark_hana,
        CardCompany.HANA
    ),
    CardCompany.KB to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kb,
        R.color.card_kb,
        R.drawable.symbol_mark_kb,
        CardCompany.KB
    ),
)