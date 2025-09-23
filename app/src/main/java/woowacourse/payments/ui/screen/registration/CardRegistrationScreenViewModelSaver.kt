package woowacourse.payments.ui.screen.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.screen.registration.CardRegistrationScreenViewModel.Companion.saver

@Composable
fun rememberCardRegistrationScreenViewModel(initialUiState: CardRegistrationScreenUiState? = null): CardRegistrationScreenViewModel =
    rememberSaveable(saver = saver) {
        initialUiState?.let(::CardRegistrationScreenViewModel) ?: CardRegistrationScreenViewModel()
    }
