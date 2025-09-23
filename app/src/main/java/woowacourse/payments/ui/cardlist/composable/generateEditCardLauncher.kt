package woowacourse.payments.ui.cardlist.composable

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.addcard.EditCardActivity
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.util.getParcelableExtraCompat

@Composable
fun generateEditCardLauncher(
    editCard: (Card) -> Unit,
    context: Context,
) = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val card = result.data?.getParcelableExtraCompat<CardUiModel>(EditCardActivity.EXTRA_CARD)
        if (card != null) {
            editCard(card.toDomain())
            Toast
                .makeText(
                    context,
                    context.getString(R.string.card_list_card_edited_toast),
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }
}
