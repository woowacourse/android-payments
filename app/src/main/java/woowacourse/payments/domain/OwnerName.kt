package woowacourse.payments.domain

class OwnerName(
    val name: String
) {
    init {
        require(name.length <= MAX_LENGTH)
    }

    companion object {
        const val MAX_LENGTH = 30
    }
}
