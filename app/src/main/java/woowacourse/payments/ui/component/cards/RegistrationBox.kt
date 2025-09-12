package woowacourse.payments.ui.component.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.IconGray

@Composable
fun RegistrationBox(
    onClickRegistration: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val registrationBoxAreaDescription =
        stringResource(R.string.registration_box_registration_box_description)
    Box(
        modifier =
            modifier
                .semantics { contentDescription = registrationBoxAreaDescription }
                .size(width = 208.dp, height = 124.dp)
                .background(Color.LightGray)
                .clickable { onClickRegistration() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.registration_box_add_icon_description),
            modifier = Modifier.size(34.dp),
            tint = IconGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationBoxPreview() {
    RegistrationBox(
        onClickRegistration = { },
    )
}
