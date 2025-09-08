package woowacourse.payments.ui.view.cards

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import woowacourse.payments.ui.component.PaymentToolbar
import woowacourse.payments.ui.core.CardType
import woowacourse.payments.ui.core.Event
import woowacourse.payments.ui.core.getParcelableCompat
import woowacourse.payments.ui.serialization.SerializationCard
import woowacourse.payments.ui.view.cards.CardsActivity.Companion.EXTRA_CARD

@Composable
fun CardsRoute(
    onAddCardClick: (ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit
) {
    val cards = rememberSaveable { mutableStateListOf<SerializationCard>() }
    var uiEvent by remember {
        mutableStateOf<Event<CardScreenUiEvent>>(
            Event(CardScreenUiEvent.Idle)
        )
    }

    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.getParcelableCompat<SerializationCard>(EXTRA_CARD)?.let { newCard ->
                cards.add(newCard)
                uiEvent = Event(CardScreenUiEvent.CompleteAddCard)
            }
        }
    }

    Scaffold(
        topBar = {
            PaymentToolbar(
                onAddClick = {
                    onAddCardClick(activityResultLauncher)
                },
                addButtonVisible = cards.size > 1
            )
        }
    ) { innerPadding ->
        CardsScreen(
            cards = cards.map { it.toDomain() },
            uiEvent = uiEvent,
            onClickCard = { cardType ->
                if (cardType == CardType.EMPTY) {
                    onAddCardClick(activityResultLauncher)
                }
            },
            Modifier
                .padding(innerPadding)
        )
    }
}
