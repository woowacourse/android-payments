package woowacourse.payments.data

import woowacourse.payments.R
import woowacourse.payments.ui.model.Company

object CompanyRepository {
    fun getCompanies(): List<Company> =
        listOf(
            Company(R.drawable.ic_bc, "BC카드"),
            Company(R.drawable.ic_sinhan, "신한카드"),
            Company(R.drawable.ic_kakao, "카카오뱅크"),
            Company(R.drawable.ic_hyundae, "현대카드"),
            Company(R.drawable.ic_woori, "우리카드"),
            Company(R.drawable.ic_lotte, "롯데카드"),
            Company(R.drawable.ic_hana, "하나카드"),
            Company(R.drawable.ic_kb, "국민카드"),
        )
}
