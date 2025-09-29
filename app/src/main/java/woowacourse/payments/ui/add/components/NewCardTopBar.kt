package woowacourse.payments.ui.add.components

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
import woowacourse.payments.R
import woowacourse.payments.ui.add.AddMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    saveEnabled: Boolean = true,
    mode: AddMode,
) {
    TopAppBar(
        title = {
            Text(
                when (mode) {
                    is AddMode.Edit -> stringResource(R.string.title_edit_card)
                    AddMode.Create -> stringResource(R.string.title_add_card)
                },
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.desc_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSaveClick,
                enabled = saveEnabled,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.desc_done),
                )
            }
        },
        modifier = modifier,
    )
}
