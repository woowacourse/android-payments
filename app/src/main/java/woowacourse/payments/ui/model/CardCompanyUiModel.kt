package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.SymbolColor


@Parcelize
sealed interface CardCompanyUiModel : Parcelable {
    data class Default(
        @StringRes val displayName: Int = R.string.card_company_default,
        val color: Int = Black.value.toInt()
    ) : CardCompanyUiModel

    data class SelectCardCompany(
        @StringRes val displayName: Int,
        val color: Int,
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
        SymbolColor.BC.value.toInt(),
        R.drawable.symbol_mark_bc,
        CardCompany.BC
    ),
    CardCompany.SHINHAN to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_shinhan,
        SymbolColor.SHINHAN.value.toInt(),
        R.drawable.symbol_mark_shinhan,
        CardCompany.SHINHAN
    ),
    CardCompany.KAKAO to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kakao,
        SymbolColor.KAKAO.value.toInt(),
        R.drawable.symbol_mark_kakao,
        CardCompany.KAKAO
    ),
    CardCompany.HYUNDAE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hyundae,
        SymbolColor.HYUNDAE.value.toInt(),
        R.drawable.symbol_mark_hyundae,
        CardCompany.HYUNDAE
    ),
    CardCompany.WOORI to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_woori,
        SymbolColor.WOORI.value.toInt(),
        R.drawable.symbol_mark_woori,
        CardCompany.WOORI
    ),
    CardCompany.LOTTE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_lotte,
        SymbolColor.LOTTE.value.toInt(),
        R.drawable.symbol_mark_lotte,
        CardCompany.LOTTE
    ),
    CardCompany.HANA to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hana,
        SymbolColor.HANA.value.toInt(),
        R.drawable.symbol_mark_hana,
        CardCompany.HANA
    ),
    CardCompany.KB to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kb,
        SymbolColor.HANA.value.toInt(),
        R.drawable.symbol_mark_kb,
        CardCompany.KB
    ),
)