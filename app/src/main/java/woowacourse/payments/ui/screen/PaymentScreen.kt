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
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentScreen() {
    val paymentStateHolder = rememberPaymentStateHolder()
    val context = LocalContext.current

    var editId by remember { mutableStateOf<Long?>(null) }

    val cardLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            val id = editId
            editId = null
            val card = AddCardActivity.parseResult(result.data)?.toDomain()
            paymentStateHolder.applyById(id, card)
        }

    PaymentContent(
        cards = paymentStateHolder.uiCards,
        showTopAdd = paymentStateHolder.showTopAdd,
        onAddCardClick = {
            editId = null
            cardLauncher.launch(AddCardActivity.newIntent(context))
        },
        onCardClick = { id ->
            val card = paymentStateHolder.uiCards.getOrNull(id) ?: return@PaymentContent
            editId = card.id
            cardLauncher.launch(AddCardActivity.newIntent(context, card))
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
