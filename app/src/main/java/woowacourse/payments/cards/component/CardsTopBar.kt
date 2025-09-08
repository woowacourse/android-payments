package woowacourse.payments.cards.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    isAddable: Boolean,
) {
    TopAppBar(
        title = {
            Text(
                "Payments",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            if (isAddable) {
                TextButton(onClick = { onAddClick() }) {
                    Text("추가", fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
