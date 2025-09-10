package woowacourse.payments.ui.cardlist.composable

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.cardlist.util.navigateToAddCard

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardListTopBar(
    context: Context,
    addCardLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    showAddCardBtn: Boolean,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(stringResource(R.string.card_list_title)) },
        actions = {
            if (showAddCardBtn) {
                TextButton(onClick = { navigateToAddCard(context, addCardLauncher) }) {
                    Text(
                        stringResource(R.string.card_list_add_card_button),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
    )
}
