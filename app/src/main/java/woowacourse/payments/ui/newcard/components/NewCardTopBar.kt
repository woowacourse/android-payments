package woowacourse.payments.ui.newcard.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.designsystem.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.new_card_add),
) {
    TopAppBar(
        title = { Text(title, color = Black) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.new_card_navigate_to_back),
                    tint = Black,
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.new_card_save),
                    tint = Black,
                )
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                titleContentColor = Black,
                navigationIconContentColor = Black,
                actionIconContentColor = Black,
            ),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    AndroidpaymentsTheme {
        NewCardTopBar(onBackClick = {}, onSaveClick = {})
    }
}
