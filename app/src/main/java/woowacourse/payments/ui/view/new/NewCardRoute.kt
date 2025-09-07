package woowacourse.payments.ui.view.new

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.serialization.toSerializationCard

@Composable
fun NewCardRoute(
    onBackClick: () -> Unit,
    onSaveClick: (Card) -> Unit
) {
    var card by rememberSaveable { mutableStateOf(Card.EMPTY.toSerializationCard()) }
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackClick() },
                onSaveClick = { onSaveClick(card.toDomain()) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NewCardScreen(
            card = card.toDomain(),
            onCardChange = { card = it.toSerializationCard() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
