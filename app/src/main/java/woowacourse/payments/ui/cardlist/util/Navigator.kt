package woowacourse.payments.ui.cardlist.util

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import woowacourse.payments.ui.addcard.AddCardActivity

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
