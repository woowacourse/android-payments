package woowacourse.payments.ui.cards.components

import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    isAddable: Boolean = false,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.cards_top_bar_title),
            )
        },
        actions = {
            if (isAddable) {
                Text(
                    modifier =
                        modifier
                            .clickable(true) {
                                onAddClick()
                            }.padding(end = 20.dp),
                    text = stringResource(R.string.cards_top_bar_add),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        },
    )
}
