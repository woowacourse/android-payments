package woowacourse.payments.domain.model

enum class BankType {
    NOT_SELECTED,
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
        fun BankType.name(): String =
            when (this) {
                NOT_SELECTED -> ""
                BC -> "BC카드"
                KB -> "국민카드"
                WOORI -> "우리카드"
                SHINHAN -> "신한카드"
                KAKAO -> "카카오뱅크"
                HYUNDAE -> "현대카드"
                LOTTE -> "롯데카드"
                HANA -> "하나카드"
            }

        fun BankType.toColor(): Int =
            when (this) {
                NOT_SELECTED -> (0xFF333333).toInt()
                BC -> (0xFFF04651).toInt()
                KB -> (0xFF695F54).toInt()
                WOORI -> (0xFF20C4F4).toInt()
                SHINHAN -> (0xFF20C4F4).toInt()
                KAKAO -> (0xFFFFE300).toInt()
                HYUNDAE -> (0xFF000000).toInt()
                LOTTE -> (0xFFDA291C).toInt()
                HANA -> (0xFF008485).toInt()
            }
    }
}
