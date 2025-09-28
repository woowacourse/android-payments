package woowacourse.payments.ui.cards.single

sealed interface SingleCardsUiEvent {
    data class AddCard(val cards: List<Long>) : SingleCardsUiEvent
    data object UpdateCard : SingleCardsUiEvent
}