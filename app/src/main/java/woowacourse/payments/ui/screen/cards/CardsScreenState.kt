package woowacourse.payments.ui.screen.cards

enum class CardsScreenState {
    EMPTY,
    SINGLE,
    MULTIPLE,
    ;

    fun isVisibleRegistrationInTopBar(): Boolean = this == MULTIPLE

    fun isVisibleRegistrationInContent(): Boolean = this == EMPTY || this == SINGLE

    companion object {
        fun from(numberOfCards: Int): CardsScreenState =
            when (numberOfCards) {
                0 -> EMPTY
                1 -> SINGLE
                else -> MULTIPLE
            }
    }
}
