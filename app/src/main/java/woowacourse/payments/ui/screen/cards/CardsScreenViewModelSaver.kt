package woowacourse.payments.ui.screen.cards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import woowacourse.payments.ui.screen.cards.CardsScreenViewModel.Companion.saver

@Composable
fun rememberCardsScreenViewModel(): CardsScreenViewModel =
    rememberSaveable(saver = saver) {
        CardsScreenViewModel()
    }
