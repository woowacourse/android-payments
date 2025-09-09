package woowacourse.payments.ui.allcards

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.addcard.AddCardActivity
import woowacourse.payments.ui.addcard.AddCardScreen
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.allcards.component.AllCardsTopbar
import woowacourse.payments.ui.allcards.component.PlusCard
import woowacourse.payments.ui.component.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme


@Composable
fun AllCardsScreen(
    cards: SnapshotStateList<CardInfoUiState>,
    modifier: Modifier = Modifier,
    onPlusCardClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (cards.isEmpty()) {
            NotifyToAddCard()
            Spacer(modifier = Modifier.height(32.dp))
            PlusCard(
                onClick = onPlusCardClick
            )
            return
        }

        if (cards.size == 1) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(cardInfoUiState = cards.first())
            Spacer(modifier = Modifier.height(36.dp))
            PlusCard(
                onClick = onPlusCardClick
            )
            return
        }

        Spacer(modifier = Modifier.height(12.dp))
        cards.forEach { cardInfoUiState ->
            Card(cardInfoUiState = cardInfoUiState)
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Composable
private fun NotifyToAddCard() {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(R.string.allcards_request_add_card),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllCardsScreenPreview() {
    AndroidpaymentsTheme {
        val cards = rememberSaveable { mutableStateListOf<CardInfoUiState>() }
        Scaffold(
            topBar = {
                AllCardsTopbar(cards)
            }
        ) {
            AllCardsScreen(
                cards = cards,
                modifier = Modifier.padding(it)
            )
        }
    }
}

