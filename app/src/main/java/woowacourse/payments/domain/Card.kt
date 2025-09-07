package woowacourse.payments.domain

data class Card(
    val number: CardNumber?,
    val expired: Expired?,
    val owner: CardOwner?,
    val password: Password?,
)
