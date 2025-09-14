package woowacourse.payments.ui.cardList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.GrayFF575757
import woowacourse.payments.ui.theme.GrayFFE5E5E5

@Composable
fun RegistrationCard(
    onRegistrationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .size(208.dp, 124.dp)
                .background(
                    color = GrayFFE5E5E5,
                    shape = RoundedCornerShape(5.dp),
                ).clickable { onRegistrationClick() },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            tint = GrayFF575757,
            contentDescription = stringResource(R.string.registration_card_plus_icon_description),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationCardPreview() {
    RegistrationCard({})
}
