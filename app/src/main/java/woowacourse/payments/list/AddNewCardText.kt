package woowacourse.payments.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@Composable
fun AddNewCardText() {
    Text(
        text = stringResource(R.string.add_new_card_prompt),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
}
