package woowacourse.payments.ui.newcard.uiModel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.SymbolColorBC
import woowacourse.payments.ui.theme.SymbolColorHANA
import woowacourse.payments.ui.theme.SymbolColorHYUNDAE
import woowacourse.payments.ui.theme.SymbolColorKAKAO
import woowacourse.payments.ui.theme.SymbolColorKB
import woowacourse.payments.ui.theme.SymbolColorLOTTE
import woowacourse.payments.ui.theme.SymbolColorSHINHAN
import woowacourse.payments.ui.theme.SymbolColorWOORI

sealed interface CardCompanyUiModel {
    data class Default(
        @StringRes val displayName: Int = R.string.card_company_default,
        val color: Color = Black
    ) : CardCompanyUiModel

    data class SelectCardCompany(
        @StringRes val displayName: Int,
        val color: Color,
        @DrawableRes val logo: Int,
        val company: CardCompany,
    ) : CardCompanyUiModel
}

fun CardCompany.toUiModel(): CardCompanyUiModel.SelectCardCompany = cardCompanyMap.getValue(this)
fun CardCompanyUiModel.toDomain(): CardCompany? =
    when (this) {
        is CardCompanyUiModel.Default -> null
        is CardCompanyUiModel.SelectCardCompany -> this.company
    }

val cardCompanyMap: Map<CardCompany, CardCompanyUiModel.SelectCardCompany> = mapOf(
    CardCompany.BC to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_bc,
        SymbolColorBC,
        R.drawable.symbol_mark_bc,
        CardCompany.BC
    ),
    CardCompany.SHINHAN to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_shinhan,
        SymbolColorSHINHAN,
        R.drawable.symbol_mark_shinhan,
        CardCompany.SHINHAN
    ),
    CardCompany.KAKAO to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kakao,
        SymbolColorKAKAO,
        R.drawable.symbol_mark_kakao,
        CardCompany.KAKAO
    ),
    CardCompany.HYUNDAE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hyundae,
        SymbolColorHYUNDAE,
        R.drawable.symbol_mark_hyundae,
        CardCompany.HYUNDAE
    ),
    CardCompany.WOORI to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_woori,
        SymbolColorWOORI,
        R.drawable.symbol_mark_woori,
        CardCompany.WOORI
    ),
    CardCompany.LOTTE to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_lotte,
        SymbolColorLOTTE,
        R.drawable.symbol_mark_lotte,
        CardCompany.LOTTE
    ),
    CardCompany.HANA to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_hana,
        SymbolColorHANA,
        R.drawable.symbol_mark_hana,
        CardCompany.HANA
    ),
    CardCompany.KB to CardCompanyUiModel.SelectCardCompany(
        R.string.card_company_kb,
        SymbolColorKB,
        R.drawable.symbol_mark_kb,
        CardCompany.KB
    ),
)