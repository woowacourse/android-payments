package woowacourse.payments.domain

@JvmInline
value class CardCompany(
    val name: String,
) {
    companion object {
        val ALL: List<CardCompany> =
            listOf(
                CardCompany("BC카드"),
                CardCompany("신한카드"),
                CardCompany("카카오뱅크"),
                CardCompany("현대카드"),
                CardCompany("우리카드"),
                CardCompany("롯데카드"),
                CardCompany("하나카드"),
                CardCompany("국민카드"),
            )
    }
}
