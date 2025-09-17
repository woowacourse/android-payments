package woowacourse.payments.ui.payments.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import woowacourse.payments.R

@Parcelize
enum class BankUiModel(
    @StringRes val bankName: Int,
    @ColorRes val bankColor: Int,
    @DrawableRes val bankLogo: Int,
) : Parcelable {
    NOT_SELECTED(
        R.string.bank_ui_model_not_selected_bank_name,
        R.color.black,
        R.drawable.ic_launcher_background,
    ),
    BC(R.string.bank_ui_model_bc_bank_name, R.color.teal_200, R.drawable.ic_bc_bank),
    SHINHAN(R.string.bank_ui_model_shinhan_bank_name, R.color.teal_200, R.drawable.ic_shinhan_bank),
    KAKAO(R.string.bank_ui_model_kakao_bank_name, R.color.teal_200, R.drawable.ic_kakao_bank),
    HYUNDAI(R.string.bank_ui_model_hyundai_bank_name, R.color.teal_200, R.drawable.ic_hyundai_bank),
    WOORI(R.string.bank_ui_model_woori_bank_name, R.color.teal_200, R.drawable.ic_woori_bank),
    LOTTE(R.string.bank_ui_model_lotte_bank_name, R.color.teal_200, R.drawable.ic_lotte_bank),
    HANA(R.string.bank_ui_model_hana_bank_name, R.color.teal_200, R.drawable.ic_hana_bank),
    KB(R.string.bank_ui_model_kb_bank_name, R.color.teal_200, R.drawable.ic_kb_bank),
}
