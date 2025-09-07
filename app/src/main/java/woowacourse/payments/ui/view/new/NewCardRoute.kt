package woowacourse.payments.ui.view.new

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.NewCardTopBar

@Composable
fun NewCardRoute(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit
) {
    var card by remember { mutableStateOf(Card.EMPTY) }
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = { onSaveClick(card) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NewCardScreen(
            card = card,
            onCardChange = { card = it },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
