package woowacourse.payments.ui.cardlist

import android.R.attr.onClick
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(onAddCard: () -> Unit = {}) {
    TopAppBar(
        title = {
            Text(
                text = "Payments",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            Text(
                text = "추가",
                fontWeight = FontWeight.Bold,
                modifier =
                    Modifier.padding(end = 16.dp).clickable {
                        onAddCard()
                    },
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun CardListTopBarPreview() {
    AndroidpaymentsTheme {
        CardListTopBar()
    }
}
