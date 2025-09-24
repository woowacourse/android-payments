package woowacourse.payments.ui.newcard.components

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
fun NewCardTopBar(
    isSavable: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("카드 추가") },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.new_card_top_bar_back_icon_description),
                )
            }
        },
        actions = {
            if (isSavable) {
                IconButton(onClick = { onSaveClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(R.string.new_card_top_bar_check_icon_description),
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTopBarPreview() {
    NewCardTopBar(isSavable = true, onBackClick = {}, onSaveClick = {})
}
