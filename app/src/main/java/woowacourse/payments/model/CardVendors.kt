package woowacourse.payments.model

sealed class CardVendors {
    data object BCCard : CardVendors()

    data object ShinHanCard : CardVendors()

    data object KakaoBank : CardVendors()

    data object HyundaiCard : CardVendors()

    data object WooriCard : CardVendors()

    data object LotteCard : CardVendors()

    data object HanaCard : CardVendors()

    data object KBCard : CardVendors()
}
