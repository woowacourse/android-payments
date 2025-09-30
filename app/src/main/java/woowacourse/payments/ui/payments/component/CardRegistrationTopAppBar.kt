package woowacourse.payments.ui.payments.component

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R

private const val CARD_REGISTRATION_TOP_BAR_PREVIOUS_BUTTON_TEST_TAG =
    "CardRegistrationTopBarPreviousButtonTestTag"
private const val CARD_REGISTRATION_TOP_BAR_REGISTRATION_BUTTON_TEST_TAG =
    "CardRegistrationTopBarRegistrationButtonTestTag"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardRegistrationTopAppBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    topBarTitle: String,
    modifier: Modifier = Modifier,
    isSaveButtonEnabled: Boolean = false,
) {
    TopAppBar(
        title = { Text(text = topBarTitle) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag(CARD_REGISTRATION_TOP_BAR_PREVIOUS_BUTTON_TEST_TAG),
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.icon_previous_content_description),
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag(CARD_REGISTRATION_TOP_BAR_REGISTRATION_BUTTON_TEST_TAG),
                onClick = onSaveClick,
                enabled = isSaveButtonEnabled,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.icon_save_content_description),
                )
            }
        },
        modifier = modifier,
    )
}
