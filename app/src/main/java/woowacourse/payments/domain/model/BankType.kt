package woowacourse.payments.domain.model

enum class BankType {
    NONE,
    BC,
    KB,
    WOORI,
    SHINHAN,
    KAKAO,
    HYUNDAE,
    LOTTE,
    HANA,
    ;

    companion object {
        fun BankType.label(): String =
            when (this) {
                NONE -> ""
                BC -> "BC카드"
                KB -> "국민카드"
                WOORI -> "우리카드"
                SHINHAN -> "신한카드"
                KAKAO -> "카카오뱅크"
                HYUNDAE -> "현대카드"
                LOTTE -> "롯데카드"
                HANA -> "하나카드"
            }
    }
}
