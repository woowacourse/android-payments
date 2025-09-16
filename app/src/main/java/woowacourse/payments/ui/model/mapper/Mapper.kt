package woowacourse.payments.ui.model.mapper

import woowacourse.payments.R
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.domain.model.PaymentCard
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.theme.BC
import woowacourse.payments.ui.theme.HANA
import woowacourse.payments.ui.theme.HYUNDAE
import woowacourse.payments.ui.theme.KAKAO
import woowacourse.payments.ui.theme.KB
import woowacourse.payments.ui.theme.LOTTE
import woowacourse.payments.ui.theme.SHINHAN
import woowacourse.payments.ui.theme.WOORI

fun PaymentCard.toUiModel(): PaymentCardUiModel =
    PaymentCardUiModel(
        cardNumber = cardNumber.value,
        expiry = expiry.value,
        owner = owner,
    )

fun BankType.toUiModel(): BankUiModel? =
    when (this) {
        BankType.NOT_SELECTED -> null
        BankType.BC -> BankUiModel(R.drawable.bc, R.string.bc_card_name, BC)
        BankType.SHINHAN -> BankUiModel(R.drawable.shinhan, R.string.shinhan_card_name, SHINHAN)
        BankType.KAKAO -> BankUiModel(R.drawable.kakao, R.string.kakao_card_name, KAKAO)
        BankType.HYUNDAE -> BankUiModel(R.drawable.hyundae, R.string.hyundae_card_name, HYUNDAE)
        BankType.WOORI -> BankUiModel(R.drawable.woori, R.string.woori_card_name, WOORI)
        BankType.LOTTE -> BankUiModel(R.drawable.lotte, R.string.lotte_card_name, LOTTE)
        BankType.HANA -> BankUiModel(R.drawable.hana, R.string.hana_card_name, HANA)
        BankType.KB -> BankUiModel(R.drawable.kb, R.string.kb_card_name, KB)
    }
