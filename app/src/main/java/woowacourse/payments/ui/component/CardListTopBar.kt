package woowacourse.payments.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    showAddButton: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Payments", fontWeight = FontWeight.Normal) },
        actions = {
            if (showAddButton) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = "추가",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
fun CardListTopBarPreview() {
    AndroidpaymentsTheme {
        CardListTopBar(
            true,
            {},
        )
    }
}
