package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Blue
import woowacourse.payments.ui.theme.Brown
import woowacourse.payments.ui.theme.DeepDarkGray
import woowacourse.payments.ui.theme.Green
import woowacourse.payments.ui.theme.Purple
import woowacourse.payments.ui.theme.Red
import woowacourse.payments.ui.theme.SkyBlue
import woowacourse.payments.ui.theme.Yellow

@Parcelize
enum class CardCompanyUiModel(
    @StringRes val companyName: Int,
    @DrawableRes val image: Int,
    private val colorValue: ULong,
) : Parcelable {
    BC(R.string.card_company_ui_model_bc, R.drawable.ic_bc_card_symbol, Red.value),
    SHINHAN(R.string.card_company_ui_model_shinhan, R.drawable.ic_shinhan_symbol, Blue.value),
    KAKAO(R.string.card_company_ui_model_kakao, R.drawable.ic_kakao_symbol, Yellow.value),
    HYUNDAE(R.string.card_company_ui_model_hyundae, R.drawable.ic_hyundae_symbol, Black.value),
    WOORI(R.string.card_company_ui_model_woori, R.drawable.ic_woori_symbol, SkyBlue.value),
    LOTTE(R.string.card_company_ui_model_lotte, R.drawable.ic_lotte_symbol, Purple.value),
    HANA(R.string.card_company_ui_model_hana, R.drawable.ic_hana_symbol, Green.value),
    KB(R.string.card_company_ui_model_kb, R.drawable.ic_kb_symbol, Brown.value),
    NOT_SELECT(
        R.string.card_company_ui_model_not_select,
        R.drawable.ic_default_symbol,
        DeepDarkGray.value,
    ),
    ;

    val color: Color get() = Color(colorValue)

    fun isSelect(): Boolean = this != NOT_SELECT
}
