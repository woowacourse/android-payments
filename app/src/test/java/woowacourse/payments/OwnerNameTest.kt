package woowacourse.payments

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.OwnerName

class OwnerNameTest {
    @Test
    fun 소유자_이름은_30자를_넘길_수_없다() {
        // given
        val ownerName = "크림".repeat(20)

        // when & then
        shouldThrow<IllegalArgumentException> {
            OwnerName(ownerName)
        }
    }
}
