package woowacourse.payments.ui.CardCatalog.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.theme.Black
import woowacourse.payments.ui.theme.Black1D


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCatalogTopBar(
    cards: List<Card>,
    onAddNewCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.payments_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.W400,
                color = Black1D
            )
        },
        actions = {
            if (cards.size >= 2) {
                TextButton(onClick = {
                    onAddNewCardClick()
                }) {
                    Text(text = stringResource(R.string.payments_top_bar_icon_name), fontSize = 18.sp, fontWeight = FontWeight.W700, color = Black)
                }
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun CardCatalogTopBarPreview() {
    CardCatalogTopBar(emptyList(), onAddNewCardClick = {})
}