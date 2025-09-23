package woowacourse.payments.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import woowacourse.payments.AddCardActivity
import woowacourse.payments.R
import woowacourse.payments.ui.mapper.CardMapper.toDomain
import woowacourse.payments.ui.model.toUiModel

@Composable
fun PaymentScreen() {
    val state = rememberPaymentStateHolder()
    val context = LocalContext.current

    val cardLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { res ->
            if (res.resultCode == Activity.RESULT_OK) {
                AddCardActivity
                    .parseResult(res.data)
                    ?.toDomain()
                    ?.let(state::applyResult)
            } else {
                state.cancelEdit()
            }
        }

    LaunchedEffect(state.cards.size) {
        if (state.cards.isNotEmpty()) {
            Toast
                .makeText(
                    context,
                    context.getString(R.string.payment_toast_card_added),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    PaymentContent(
        cards = state.uiCards,
        showTopAdd = state.showTopAdd,
        canAddMore = state.canAddMore,
        onAddCardClick = { cardLauncher.launch(AddCardActivity.newIntent(context)) },
        onCardClick = { _, index ->
            state
                .beginEditAt(index)
                .takeIf { it }
                ?.let {
                    state.cards.getOrNull(index)?.let { domain ->
                        cardLauncher.launch(
                            AddCardActivity.newIntent(
                                context,
                                domain.toUiModel(),
                            ),
                        )
                    } ?: state.cancelEdit()
                }
        },
    )
}
