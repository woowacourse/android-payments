package woowacourse.payments.component

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.NewCardScreenActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWalletTopBar(
    isAddable: Boolean = false,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    CenterAlignedTopAppBar(
        title = {
            Text("Payments")
        },
        actions = {
            if (isAddable) {
                Text(
                    modifier =
                        modifier
                            .clickable {
                                onAddCardClick()
                            }
                            .padding(end = 20.dp),
                    text = "추가",
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp,
                )
            }
        },
    )
}

@Composable
@Preview(showBackground = true)
fun CardWalletTopBarPreview() {
    CardWalletTopBar(
        isAddable = true,
        onAddCardClick = {}
    )
}
