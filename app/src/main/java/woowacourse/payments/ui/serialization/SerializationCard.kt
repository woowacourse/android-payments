package woowacourse.payments.ui.serialization

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.BankType

@Parcelize
data class SerializationCard(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
    val bank: BankType,
) : Parcelable {
    fun toDomain(): Card =
        Card(
            number = number,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
            bank = bank,
        )
}

fun Card.toSerializationCard(): SerializationCard =
    SerializationCard(
        number = number,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password,
        bank = bank,
    )
