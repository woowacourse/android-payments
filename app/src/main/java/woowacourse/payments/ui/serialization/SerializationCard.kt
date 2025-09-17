package woowacourse.payments.ui.serialization

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.state.CardCompanyState

@Parcelize
data class SerializationCard(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
    val bank: CardCompanyState,
) : Parcelable {
    fun toDomain(): Card =
        Card(
            number = number,
            expireDate = expireDate,
            ownerName = ownerName,
            password = password,
            company = bank,
        )
}

fun Card.toSerializationCard(): SerializationCard =
    SerializationCard(
        number = number,
        expireDate = expireDate,
        ownerName = ownerName,
        password = password,
        bank = company,
    )
