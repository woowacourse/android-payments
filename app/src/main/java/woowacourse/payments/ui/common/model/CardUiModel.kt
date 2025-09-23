package woowacourse.payments.ui.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.data.model.request.CardRequest
import woowacourse.payments.data.model.request.EditCardRequest
import woowacourse.payments.data.model.request.NewCardRequest
import woowacourse.payments.data.model.response.CardResponse
import woowacourse.payments.domain.model.BankType

@Parcelize
data class CardUiModel(
    val id: Long?,
    val numberDigits: String,
    val expiry: String,
    val holder: String,
    val bankType: BankType,
) : Parcelable

fun CardUiModel.toData(): CardRequest =
    if (id == null) {
        NewCardRequest(
            numberDigits = numberDigits,
            expiry = expiry,
            holder = holder,
            bankType = bankType.name,
        )
    } else {
        EditCardRequest(
            id = id,
            numberDigits = numberDigits,
            expiry = expiry,
            holder = holder,
            bankType = bankType.name,
        )
    }

fun CardResponse.toUi(): CardUiModel =
    CardUiModel(
        id = id,
        numberDigits = numberDigits,
        expiry = expiry,
        holder = holder,
        bankType = BankType.valueOf(bankType),
    )
