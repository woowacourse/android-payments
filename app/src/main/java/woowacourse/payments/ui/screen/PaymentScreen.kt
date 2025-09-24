package woowacourse.payments.ui.screen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.AddCardActivity
import woowacourse.payments.ui.mapper.CardMapper.toDomain
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen() {
    val state = rememberPaymentStateHolder()
    val context = LocalContext.current

    val cardLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                AddCardActivity
                    .parseResult(result.data)
                    ?.toDomain()
                    ?.let(state::applyResult)
            } else {
                state.cancelEdit()
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

@Preview(name = "기본 화면")
@Composable
private fun PaymentScreenPreview() {
    AndroidpaymentsTheme {
        PaymentScreen()
    }
}
