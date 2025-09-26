package woowacourse.payments.ui.screen.cardAddition.component

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAdditionTopBar(
    title: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCompletable: Boolean = false,
) {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.card_addition_top_bar_back),
                )
            }
        },
        actions = {
            IconButton(
                onClick = onSaveClick,
                modifier =
                    Modifier.semantics {
                        contentDescription =
                            context.getString(R.string.card_addition_complete_button_description)
                    },
                enabled = isCompletable,
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.card_addition_top_bar_complete),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CardAdditionTopBarPreview() {
    CardAdditionTopBar(
        title = "카드 추가",
        onBackClick = {},
        onSaveClick = {},
    )
}
