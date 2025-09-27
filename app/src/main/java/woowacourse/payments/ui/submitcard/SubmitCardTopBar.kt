package woowacourse.payments.ui.submitcard

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
fun SubmitCardTopBar(
    stateHolder: SubmitCardStateHolder,
    onSubmitClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                when (stateHolder) {
                    is SubmitCardStateHolder.AddCardStateHolder -> stringResource(R.string.submit_card_add_title)
                    is SubmitCardStateHolder.EditCardStateHolder -> stringResource(R.string.submit_card_edit_title)
                },
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.submit_card_back_button_description),
                )
            }
        },
        actions = {
            IconButton(onClick = { onSubmitClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.submit_card_submit_button_description),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "카드 정보 제출 상단 바")
@Composable
private fun SubmitCardTopBarPreview() {
    SubmitCardTopBar(
        stateHolder = SubmitCardStateHolder.AddCardStateHolder(),
        onSubmitClick = {},
        onBackClick = {},
    )
}
