package woowacourse.payments.ui.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.TextGray

@Composable
fun RegistrationBox(
    onClickRegistration: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .size(width = 208.dp, height = 124.dp)
                .background(Color.LightGray)
                .clickable { onClickRegistration() },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.cards_screen_registration_symbol),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = TextGray,
        )
    }
}
