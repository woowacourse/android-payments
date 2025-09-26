package woowacourse.payments.ui.cardform.component

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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFormTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSaveButtonEnabled: Boolean = false,
) {
    val previousButtonDescription =
        stringResource(R.string.card_registration_top_app_bar_previous_button_description)
    val saveButtonDescription =
        stringResource(R.string.card_registration_top_app_bar_save_icon_description)

    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.semantics { contentDescription = previousButtonDescription },
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.common_navigate_previous),
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.semantics { contentDescription = saveButtonDescription },
                onClick = onSaveClick,
                enabled = isSaveButtonEnabled,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = saveButtonDescription,
                )
            }
        },
        modifier = modifier,
    )
}

// @Preview
// @Composable
// private fun CardFormTopAppBarPreview() {
//    CardFormTopAppBar(
//        onBackClick = {},
//        onSaveClick = {},
//    )
// }
