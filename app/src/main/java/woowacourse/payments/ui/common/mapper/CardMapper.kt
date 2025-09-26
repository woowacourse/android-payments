import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardExpirationDateStatus
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel

fun CardCompany.toUiModel(): CardCompanyUiModel =
    when (this) {
        CardCompany.BC -> CardCompanyUiModel.BC
        CardCompany.SHINHAN -> CardCompanyUiModel.SHINHAN
        CardCompany.KAKAO -> CardCompanyUiModel.KAKAO
        CardCompany.HYUNDAE -> CardCompanyUiModel.HYUNDAE
        CardCompany.WOORI -> CardCompanyUiModel.WOORI
        CardCompany.LOTTE -> CardCompanyUiModel.LOTTE
        CardCompany.HANA -> CardCompanyUiModel.HANA
        CardCompany.KB -> CardCompanyUiModel.KB
    }

fun CardCompanyUiModel.toUiModel(): CardCompany? =
    when (this) {
        CardCompanyUiModel.BC -> CardCompany.BC
        CardCompanyUiModel.SHINHAN -> CardCompany.SHINHAN
        CardCompanyUiModel.KAKAO -> CardCompany.KAKAO
        CardCompanyUiModel.HYUNDAE -> CardCompany.HYUNDAE
        CardCompanyUiModel.WOORI -> CardCompany.WOORI
        CardCompanyUiModel.LOTTE -> CardCompany.LOTTE
        CardCompanyUiModel.HANA -> CardCompany.HANA
        CardCompanyUiModel.KB -> CardCompany.KB
        CardCompanyUiModel.NOT_SELECT -> null
    }

fun CardExpirationDateUiModel.toDomain(): CardExpirationDate {
    val cardExpirationDateStatus = CardExpirationDate.toCardExpirationDateStatus(this.value)
    return cardExpirationDateStatus.toDomain()
}

fun CardExpirationDate.toUiModel(): CardExpirationDateUiModel = CardExpirationDateUiModel("${this.month}${this.year}")

fun CardExpirationDateStatus.toDomain(): CardExpirationDate = CardExpirationDate.from(this)

fun CardUiModel.toDomain(): Card =
    Card(
        id = null,
        cardholderName = cardholderName,
        cardNumber = cardNumberUiModel.value,
        cardPassword = cardPasswordUiModel.value,
        cardCompany =
            cardCompanyUiModel.toUiModel()
                ?: run { throw IllegalStateException("존재하지 않는 카드사 입니다") },
        cardExpirationDate = cardExpirationDateUiModel.toDomain(),
    )

fun String?.toCardholderUiModel(): CardholderNameUiModel? = this?.let { CardholderNameUiModel(it) }

fun String.toCardNumberUiModel(): CardNumberUiModel = CardNumberUiModel(this)

fun String.toPasswordUiModel(): CardPasswordUiModel = CardPasswordUiModel(this)

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardCompanyUiModel = cardCompany.toUiModel(),
        cardholderNameUiModel = cardholderName.toCardholderUiModel() ?: CardholderNameUiModel(),
        cardNumberUiModel = cardNumber.toCardNumberUiModel(),
        cardExpirationDateUiModel = cardExpirationDate.toUiModel(),
        cardPasswordUiModel = cardPassword.toPasswordUiModel(),
    )
