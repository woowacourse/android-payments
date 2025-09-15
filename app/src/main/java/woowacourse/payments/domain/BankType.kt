package woowacourse.payments.domain

enum class BankType(
    val displayName: String,
) {
    BC("BC카드"),
    SHINHAN("신한카드"),
    KAKAOBANK("카카오뱅크"),
    HYUNDAI("현대카드"),
    WOORI("우리카드"),
    LOTTE("롯데카드"),
    HANA("하나카드"),
    KB("국민카드"),
    NOT_SELECTED(""),
}
