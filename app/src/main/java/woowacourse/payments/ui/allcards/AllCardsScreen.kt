package woowacourse.payments.ui.allcards

import android.content.Intent
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
import woowacourse.payments.ui.theme.AndroidpaymentsTheme


@Composable
fun AllCardsScreen(innerPadding: PaddingValues) {
    val cards = rememberSaveable { mutableStateListOf<CardInfoUiState>() }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (cards.isEmpty()) {
            NotifyToAddCard()
            Spacer(modifier = Modifier.height(32.dp))
            PlusCard(
                launcher = launcher
            )
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

@Composable
private fun PlusCard(
    launcher: ActivityResultLauncher<Intent>,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .height(124.dp)
            .width(208.dp)
            .background(
                color = colorResource(id = R.color.payments_plus_card_background),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable {
                launcher.launch(
                    AddCardActivity.getIntent(context)
                )
            }
        ,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(34.dp),
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.payments_topbar_add_card),
            tint = colorResource(id = R.color.payments_plus_card_icon_color),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllCardsScreenPreview() {
    AndroidpaymentsTheme {
        Scaffold(
            topBar = {
                AllCardsTopbar()
            }
        ) {
            AllCardsScreen(it)
        }
    }
}
