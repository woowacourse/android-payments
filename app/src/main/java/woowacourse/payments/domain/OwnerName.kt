package woowacourse.payments.domain

import woowacourse.payments.domain.exception.OwnerNamerException

@JvmInline
value class OwnerName private constructor(
    val value: String?,
) {
    init {
        value?.length?.let {
            if (it > MAX_LENGTH_OWNER_NAME) {
                throw OwnerNamerException.InvalidLength
            }
        }
    }

    companion object {
        const val MAX_LENGTH_OWNER_NAME = 30

        fun create(value: String?): Result<OwnerName> =
            runCatching {
                OwnerName(value)
            }
    }
}
