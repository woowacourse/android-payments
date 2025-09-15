package woowacourse.payments.ui.newcard.model

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun rememberBoundScrollState(holder: NewCardUiStateHolder): ScrollState {
    val scroll = rememberScrollState()

    LaunchedEffect(holder.scrollPosition) {
        if (scroll.value != holder.scrollPosition) {
            scroll.scrollTo(holder.scrollPosition)
        }
    }
    LaunchedEffect(scroll.value) {
        if (holder.scrollPosition != scroll.value) {
            holder.updateScrollPosition(scroll.value)
        }
    }
    return scroll
}
