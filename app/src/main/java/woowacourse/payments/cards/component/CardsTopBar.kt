package woowacourse.payments.cards.component

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    isAddable: Boolean,
) {
    CenterAlignedTopAppBar(
        title = {
            Text("Payments")
        },
        actions = {
            if (isAddable) {
                TextButton(onClick = { onAddClick() }) {
                    Text(
                        text = stringResource(R.string.add),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CardsTopBarPreview() {
    CardsTopBar(
        isAddable = true,
    )
}
