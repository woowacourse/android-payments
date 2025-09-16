package woowacourse.payments.ui.fixture

import woowacourse.payments.ui.model.BankTypeUiModel
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardPasswordUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

val VALIDATED_CARD_NUMBER =
    CardNumberUiModel(
        number = "1234123412341234",
        state = CardNumberUiModel.State.VALID,
    )

val VALIDATED_CARD_EXPIRATION_DATE =
    CardExpirationDateUiModel(
        expirationDate = "1234",
        state = CardExpirationDateUiModel.State.VALID,
    )

val VALIDATED_CARD_HOLDER_NAME =
    CardholderNameUiModel(
        name = "DICE",
        maxLength = 30,
        state = CardholderNameUiModel.State.VALID,
    )

val VALIDATED_CARD_PASSWORD =
    CardPasswordUiModel(
        password = "1234",
        state = CardPasswordUiModel.State.VALID,
    )

val KB_BANK = BankTypeUiModel.KB

val PAYMENT_CARD =
    PaymentCardUiModel(
        bankType = KB_BANK,
        number = VALIDATED_CARD_NUMBER,
        expirationDate = VALIDATED_CARD_EXPIRATION_DATE,
        cardholderName = VALIDATED_CARD_HOLDER_NAME,
    )
