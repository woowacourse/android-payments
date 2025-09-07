package woowacourse.payments.ui.serialization

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class SerializationCard(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
) : Parcelable {
    fun toDomain(): Card = Card(
        number = number,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password,
    )
}

fun Card.toSerializationCard(): SerializationCard = SerializationCard(
    number = number,
    expireDate = expireDate,
    ownerName = ownerName,
    password = password,
)
