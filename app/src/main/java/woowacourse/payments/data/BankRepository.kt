package woowacourse.payments.data

import woowacourse.payments.R
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.domain.model.BankType

object BankRepository {
    fun getBanks(): List<Bank> =
        listOf(
            Bank(BankType.BC, R.drawable.ic_bc),
            Bank(BankType.SHINHAN, R.drawable.ic_shinhan),
            Bank(BankType.KAKAO, R.drawable.ic_kakao),
            Bank(BankType.HYUNDAE, R.drawable.ic_hyundae),
            Bank(BankType.WOORI, R.drawable.ic_woori),
            Bank(BankType.LOTTE, R.drawable.ic_lotte),
            Bank(BankType.HANA, R.drawable.ic_hana),
            Bank(BankType.KB, R.drawable.ic_kb),
        )
}
