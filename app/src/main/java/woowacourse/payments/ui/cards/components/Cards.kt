package woowacourse.payments.ui.cards.components

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity

@Composable
fun Cards(
    cardAddLauncher: ActivityResultLauncher<Intent>,
    cardList: List<PaymentCardUiModel>,
    minimumCardCountForAddButton: Int = 0,
) {
    val context = LocalContext.current

    cardList.forEach { card ->
        CardItem(
            paymentCard = card,
        )
    }

    if (cardList.size <= minimumCardCountForAddButton) {
        if (cardList.isEmpty()) {
            Text(
                text = stringResource(R.string.cards_no_card),
            )
        }
        AddCardImage {
            val intent = NewCardActivity.newIntent(context)
            cardAddLauncher.launch(intent)
        }
    }
}
