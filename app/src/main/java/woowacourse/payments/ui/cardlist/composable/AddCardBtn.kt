package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun AddCardBtn(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize().clickable { onClick() }.background(Color(0xFFE5E5E5)),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = stringResource(R.string.add_card_plus_sign), fontSize = 34.sp, color = Color(0xFF575757))
    }
}
