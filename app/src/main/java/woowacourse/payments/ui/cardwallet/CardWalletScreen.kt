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
import woowacourse.payments.ui.cardwallet.model.rememberCardWalletState
import woowacourse.payments.ui.common.extensions.getParcelableExtraCompat
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.CardFormActivity

@Composable
fun CardWalletScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val holder = rememberCardWalletState()

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val saved = result.data?.getParcelableExtraCompat<CardUiModel>(CardFormActivity.EXTRA_CARD_RESULT)
                if (saved != null) {
                    val existed = holder.updateCard(saved)
                    val msgRes = if (existed) R.string.edit_card_success else R.string.new_card_success
                    Toast.makeText(context, getString(context, msgRes), Toast.LENGTH_SHORT).show()
                }
            }
        }

    Scaffold(
        topBar = {
            CardWalletTopBar(
                cardCount = holder.cardCount,
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
                cards = holder.cards,
                cardWalletState = holder.cardWalletState,
                navigateToNewCard = { navigateToNewCard(launcher, context) },
                navigateToEditCard = { card -> navigateToEditCard(launcher, context, card) },
            )
        }
    }
}

private fun navigateToNewCard(
    launcher: ActivityResultLauncher<Intent>,
    context: Context,
) {
    launcher.launch(CardFormActivity.newIntent(context))
}

private fun navigateToEditCard(
    launcher: ActivityResultLauncher<Intent>,
    context: Context,
    card: CardUiModel,
) {
    launcher.launch(CardFormActivity.newIntent(context, card))
}

@Preview(showBackground = true)
@Composable
private fun CardWalletScreenPreview() {
    AndroidpaymentsTheme {
        CardWalletScreen()
    }
}
