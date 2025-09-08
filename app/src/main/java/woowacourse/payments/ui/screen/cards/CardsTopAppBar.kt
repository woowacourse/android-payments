package woowacourse.payments.ui.screen.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsTopAppBar(
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    isRegistrationButtonEnabled: Boolean = false,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.cards_screen_top_app_bar_title)) },
        actions = { if (isRegistrationButtonEnabled) RegistrationButton(onRegistrationButtonClick) },
        modifier = modifier,
    )
}

@Composable
private fun RegistrationButton(
    onRegistrationButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        onClick = onRegistrationButtonClick,
        colors =
            ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.Gray,
            ),
        modifier = modifier,
    ) {
        Text(
            text = stringResource(R.string.cards_screen_top_app_bar_registration_button_text),
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun CardsTopAppBarPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        CardsTopAppBar(
            onRegistrationButtonClick = {},
            isRegistrationButtonEnabled = true,
        )
        CardsTopAppBar(
            onRegistrationButtonClick = {},
            isRegistrationButtonEnabled = false,
        )
    }
}
