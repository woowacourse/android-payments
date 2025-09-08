package woowacourse.payments.ui.allcards.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AllCardsTopbar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(R.string.payments_allcards_topbar_all_cards))
        },
        actions = {
            Text(
                text = stringResource(R.string.payments_allcards_topbar_add_cards),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    )
}
