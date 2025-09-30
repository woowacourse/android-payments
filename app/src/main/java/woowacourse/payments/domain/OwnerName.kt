package woowacourse.payments.domain

import woowacourse.payments.ui.model.OwnerNameUiModel

data class OwnerName(
    val name: String = "",
) {
    init {
        require(name.length <= CARD_OWNER_MAX_LENGTH)
    }

    fun toUiModel(): OwnerNameUiModel =
        OwnerNameUiModel(
            name = name,
        )

    companion object {
        const val CARD_OWNER_MAX_LENGTH = 30

        fun fromRawInput(name: String): OwnerName {
            val newName = name.take(CARD_OWNER_MAX_LENGTH)
            return OwnerName(newName)
        }
    }
}
