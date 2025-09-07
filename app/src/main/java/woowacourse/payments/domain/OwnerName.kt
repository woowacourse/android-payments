package woowacourse.payments.domain

data class OwnerName(
    val name: String = "",
) {
    override fun toString(): String = name

    companion object {
        const val CARD_OWNER_MAX_LENGTH = 30

        fun fromRawInput(name: String): OwnerName {
            val newName = name.take(CARD_OWNER_MAX_LENGTH)
            return OwnerName(newName)
        }
    }
}
