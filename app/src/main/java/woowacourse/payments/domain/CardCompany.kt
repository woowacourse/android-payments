package woowacourse.payments.domain

enum class CardCompany(
    val companyName: String,
) {
    BC_CARD("BC카드"),
    SHINHAN_CARD("신한카드"),
    KAKAO_BANK("카카오뱅크"),
    HYUNDAI_CARD("현대카드"),
    WOORI_CARD("우리카드"),
    LOTTE_CARD("롯데카드"),
    HANA_CARD("하나카드"),
    KB_CARD("국민카드"),
}
