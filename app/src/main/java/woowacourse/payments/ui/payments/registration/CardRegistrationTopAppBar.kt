package woowacourse.payments.ui.payments.registration

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
fun CardRegistrationTopAppBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSaveButtonEnabled: Boolean = false,
) {
    TopAppBar(
        title = { Text(stringResource(R.string.card_registration_bar_title)) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.semantics { contentDescription = "뒤로 가기 버튼" },
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
                modifier = Modifier.semantics { contentDescription = "완료 버튼" },
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
