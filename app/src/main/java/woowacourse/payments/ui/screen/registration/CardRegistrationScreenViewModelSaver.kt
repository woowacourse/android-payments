package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.screen.registration.CardRegistrationScreenViewModel.Companion.saver

@Composable
fun rememberCardRegistrationScreenViewModel(): CardRegistrationScreenViewModel =
    rememberSaveable(saver = saver) {
        CardRegistrationScreenViewModel()
    }
