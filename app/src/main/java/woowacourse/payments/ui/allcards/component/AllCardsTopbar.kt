package woowacourse.payments.ui.allcards.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AllCardsTopbar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.payments_topbar_all_cards))
        }
    )
}
