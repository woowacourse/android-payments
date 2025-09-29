package woowacourse.payments.ui.screen

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.AddCardActivity
import woowacourse.payments.ui.mapper.CardMapper.toDomain
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen() {
    val paymentStateHolder = remember { PaymentStateHolder() }
    val context = LocalContext.current

    val cardLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode != Activity.RESULT_OK) return@rememberLauncherForActivityResult

            val cardUiModel =
                AddCardActivity.parseResult(result.data) ?: return@rememberLauncherForActivityResult
            val domain = cardUiModel.toDomain()

            val exists = paymentStateHolder.uiCards.any { it.id == cardUiModel.id }
            paymentStateHolder.addOrUpdateById(if (exists) cardUiModel.id else null, domain)
        }

    PaymentContent(
        cards = paymentStateHolder.uiCards,
        showTopAdd = paymentStateHolder.showTopAdd,
        onAddCardClick = { cardLauncher.launch(AddCardActivity.newIntent(context)) },
        onCardClick = { id: String ->
            val selected =
                paymentStateHolder.uiCards.firstOrNull { it.id == id } ?: return@PaymentContent
            cardLauncher.launch(AddCardActivity.newIntent(context, selected))
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
