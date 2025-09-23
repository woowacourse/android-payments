package woowacourse.payments.ui.cardlist.util

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.addcard.EditCardActivity

fun navigateToAddCard(
    context: Context,
    addCardLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    addCardLauncher.launch(
        Intent(
            context,
            AddCardActivity::class.java,
        ),
    )
}

fun navigateToEditCard(
    context: Context,
    editCardLauncher: ActivityResultLauncher<Intent>,
    card: Card,
) {
    val intent = Intent(context, EditCardActivity::class.java)
    intent.putExtra(EditCardActivity.EXTRA_CARD, card)
    editCardLauncher.launch(intent)
}
