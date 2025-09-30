package woowacourse.payments.ui.addcard

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTopBar(
    @StringRes
    titleResId: Int,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    isOnSaveClickable: Boolean,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(titleResId)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.add_card_tool_bar_back_text),
                )
            }
        },
        actions = {
            IconButton(enabled = isOnSaveClickable, onClick = { onSaveClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.add_card_tool_bar_save_text),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun AddCardTopBarPreview() {
    CardTopBar(
        titleResId = R.string.add_card_tool_bar_title,
        onBackClick = {},
        onSaveClick = {},
        isOnSaveClickable = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun AddCardTopBarDisabledPreview() {
    CardTopBar(
        titleResId = R.string.add_card_tool_bar_title,
        onBackClick = {},
        onSaveClick = {},
        isOnSaveClickable = false,
    )
}
