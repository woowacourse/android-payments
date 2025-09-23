package woowacourse.payments.domain

data class OwnerName(
    val name: String,
) {
    init {
        require(name.length <= MAX_LENGTH)
    }

    companion object {
        const val MAX_LENGTH = 30
    }
}
