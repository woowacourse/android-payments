package woowacourse.payments.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var editIndex by remember { mutableStateOf<Int?>(null) }

    val cardLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            val index = editIndex
            editIndex = null
            state.apply(index, AddCardActivity.parseResult(result.data)?.toDomain())
        }

    PaymentContent(
        cards = state.uiCards,
        showTopAdd = state.showTopAdd,
        onAddCardClick = {
            editIndex = null
            cardLauncher.launch(AddCardActivity.newIntent(context))
        },
        onCardClick = { index ->
            val domain = state.cards.getOrNull(index) ?: return@PaymentContent
            editIndex = index
            cardLauncher.launch(
                AddCardActivity.newIntent(context, domain.toUiModel()),
            )
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
