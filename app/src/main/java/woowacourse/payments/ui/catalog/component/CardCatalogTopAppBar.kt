package woowacourse.payments.ui.catalog.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCatalogTopAppBar(
    modifier: Modifier = Modifier,
    isAddButtonVisible: Boolean = false,
    onCardAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_catalog_top_app_bar_payments),
                fontSize = 22.sp,
                modifier = Modifier,
            )
        },
        actions = {
            if (isAddButtonVisible) {
                Text(
                    text = stringResource(R.string.card_catalog_top_app_bar_add),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier =
                        Modifier
                            .clickable { onCardAddClick() }
                            .padding(end = 20.dp),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xAAAAAAAA)
@Composable
private fun CardCatalogTopAppBarPreView() {
    Column(modifier = Modifier.padding(12.dp)) {
        AndroidpaymentsTheme {
            CardCatalogTopAppBar(onCardAddClick = {})

            CardCatalogTopAppBar(onCardAddClick = {}, isAddButtonVisible = true)
        }
    }
}
