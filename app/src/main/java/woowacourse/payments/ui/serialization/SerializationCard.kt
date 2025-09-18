package woowacourse.payments.ui.serialization

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany

@Parcelize
data class SerializationCard(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
    val company: CardCompany,
) : Parcelable {
    fun toDomain(): Card =
        Card(
            number = number,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
            company = company,
        )
}

fun Card.toSerializationCard(): SerializationCard =
    SerializationCard(
        number = number,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password,
        company = company,
    )
