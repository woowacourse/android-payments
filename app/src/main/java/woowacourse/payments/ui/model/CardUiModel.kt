package woowacourse.payments.ui.model

import android.os.Parcelable
import androidx.compose.runtime.saveable.Saver
import kotlinx.parcelize.Parcelize
import woowacourse.payments.domain.Card

@Parcelize
data class CardUiModel(
    val number: CardNumberUiModel = CardNumberUiModel(),
    val expirationDate: CardExpirationDateUiModel = CardExpirationDateUiModel(),
    val ownerName: OwnerNameUiModel = OwnerNameUiModel(),
    val password: PasswordUiModel = PasswordUiModel(),
    val bank: BankTypeUiModel = BankTypeUiModel.NOT_SELECTED,
) : Parcelable {
    fun toDomain() =
        Card(
            number = number.toDomain(),
            expirationDate = expirationDate.toDomain(),
            ownerName = ownerName.toDomain(),
            password = password.toDomain(),
            bank = bank.toDomain(),
        )

    companion object {
        val Saver: Saver<CardUiModel, List<String>> =
            Saver(
                save = { cardUiModel ->
                    listOf(
                        cardUiModel.number.firstNumber, // 0
                        cardUiModel.number.secondNumber, // 1
                        cardUiModel.number.thirdNumber, // 2
                        cardUiModel.number.fourthNumber, // 3
                        cardUiModel.expirationDate.year, // 4
                        cardUiModel.expirationDate.month, // 5
                        cardUiModel.ownerName.name, // 6
                        cardUiModel.password.password, // 7
                        cardUiModel.bank.name, // 8
                    )
                },
                restore = { list ->
                    CardUiModel(
                        number = CardNumberUiModel(list[0], list[1], list[2], list[3]),
                        expirationDate = CardExpirationDateUiModel(year = list[4], month = list[5]),
                        ownerName = OwnerNameUiModel(name = list[6]),
                        password = PasswordUiModel(password = list[7]),
                        bank = BankTypeUiModel.valueOf(list[8]),
                    )
                },
            )
    }
}
