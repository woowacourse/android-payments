package woowacourse.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun InformationText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.payments_enroll_new_card),
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
        modifier = modifier
    )
    Spacer(modifier = modifier.height(32.dp))
}