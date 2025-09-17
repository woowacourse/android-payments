package woowacourse.payments

import org.junit.jupiter.api.Test
import woowacourse.payments.ui.addcard.model.VendorModalUiState

class VendorModalUiStateTest {
    @Test
    fun `상태를_숨김으로_전환할_수_있다`() {
        //given
        val vendorModalUiState = VendorModalUiState()

        //when
        vendorModalUiState.hide()

        //then
        assert(vendorModalUiState.isVisible == false)
    }

    @Test
    fun `상태를_보이게_전환할_수_있다`() {
        //given
        val vendorModalUiState = VendorModalUiState()

        //when
        vendorModalUiState.show()

        //then
        assert(vendorModalUiState.isVisible == true)
    }
}