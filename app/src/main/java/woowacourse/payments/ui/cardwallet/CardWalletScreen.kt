package woowacourse.payments.ui.cardwallet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.cardwallet.components.CardWalletContent
import woowacourse.payments.ui.cardwallet.components.CardWalletTopBar
import woowacourse.payments.ui.cardwallet.model.CardWalletState
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.NewCardActivity

@Composable
fun CardWalletScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val cards: SnapshotStateList<CardUiModel> = rememberSaveable { mutableStateListOf() }
    val state = CardWalletState.from(cards.size)

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val newCard = data?.getParcelableExtra<CardUiModel>(NewCardActivity.EXTRA_NEW_CARD_RESULT)
                if (newCard != null) {
                    cards.add(newCard)
                    Toast.makeText(context, getString(context, R.string.new_card_success), Toast.LENGTH_SHORT).show()
                }
            }
        }

    Scaffold(
        topBar = {
            CardWalletTopBar(
                state = state,
                onAddClick = { navigateToNewCard(launcher, context) },
            )
        },
        modifier =
            modifier
                .fillMaxSize()
                .statusBarsPadding(),
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CardWalletContent(
                cards = cards,
                state = state,
                navigateToNewCard = { navigateToNewCard(launcher, context) },
            )
        }
    }
}

private fun navigateToNewCard(
    launcher: ActivityResultLauncher<Intent>,
    context: Context,
) {
    launcher.launch(NewCardActivity.newIntent(context))
}

@Preview(showBackground = true)
@Composable
private fun CardWalletScreenPreview() {
    AndroidpaymentsTheme {
        CardWalletScreen()
    }
}
