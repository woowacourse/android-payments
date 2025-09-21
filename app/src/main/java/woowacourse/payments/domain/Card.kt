package woowacourse.payments.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.YearMonth

@Parcelize
class Card private constructor(
    val cardNumber: String,
    val expiryDate: YearMonth,
    val cardOwner: String? = null,
    val password: String,
    val bankType: BankType,
) : Parcelable {
    init {
        require(cardNumber.all { it.isDigit() } && cardNumber.length == CARD_NUMBER_LENGTH) { "카드번호 오류" }
        require(!expiryDate.isBefore(YearMonth.now())) { "만료일 오류" }
        if (cardOwner != null) {
            require(cardOwner.length <= CARD_OWNER_LENGTH) { "카드소유자이름 오류" }
        }
        require(password.all { it.isDigit() } && password.length == PASSWORD_LENGTH) { "비밀번호 오류" }
        require(bankType != BankType.NONE) { "카드사 선택 오류" }
    }

    companion object {
        private const val CARD_NUMBER_LENGTH: Int = 16
        private const val CARD_OWNER_LENGTH: Int = 30
        private const val PASSWORD_LENGTH: Int = 4

        fun create(
            cardNumber: String,
            expiryDate: YearMonth?,
            cardOwner: String?,
            password: String,
            bankType: BankType,
        ): Result<Card> =
            runCatching {
                expiryDate ?: throw IllegalArgumentException("만료일 날짜변환 오류")

                Card(
                    cardNumber = cardNumber,
                    expiryDate = expiryDate,
                    cardOwner = cardOwner,
                    password = password,
                    bankType = bankType,
                )
            }
    }
}
