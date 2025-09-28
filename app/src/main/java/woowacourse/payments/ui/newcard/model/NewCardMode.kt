package woowacourse.payments.ui.newcard.model

sealed interface NewCardMode {
    data object Create : NewCardMode

    data class Update(
        val cardId: Long,
    ) : NewCardMode

    companion object {
        fun of(cardId: Long): NewCardMode = if (cardId == -1L) Create else Update(cardId)
    }
}
