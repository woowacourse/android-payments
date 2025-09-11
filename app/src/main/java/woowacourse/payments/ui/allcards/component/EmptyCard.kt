package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun EmptyCard(onPlusCardClick: () -> Unit = {}) {
    NotifyToAddCard()
    Spacer(modifier = Modifier.height(32.dp))
    PlusCard(
        onClick = onPlusCardClick,
    )
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
