package woowacourse.payments.domain

data class OwnerName(
    val name: String
) {
    override fun toString(): String {
        return name
    }

    fun onValueChange(name: String): OwnerName {
        val newName = name.take(30)
        return OwnerName(newName)
    }
}