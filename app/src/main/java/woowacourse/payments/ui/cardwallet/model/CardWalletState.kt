package woowacourse.payments.ui.cardwallet.model

enum class CardWalletState {
    EMPTY,
    SINGLE,
    MULTIPLE,
    ;

    companion object {
        fun from(cardCount: Int): CardWalletState =
            when {
                cardCount <= 0 -> EMPTY
                cardCount == 1 -> SINGLE
                else -> MULTIPLE
            }
    }
}
