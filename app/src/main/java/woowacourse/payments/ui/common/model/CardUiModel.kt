package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.model.BankType

@Parcelize
data class CardUiModel(
    val numberDigits: String,
    val expiry: String,
    val holder: String,
    val bankType: BankType,
) : Parcelable
