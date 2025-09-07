package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardTopbar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.payments_topbar_add_card))
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.payments_topbar_back_icon_description)
                )
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.payments_topbar_checked_icon_description)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AddCardTopbarPreView() {
    AndroidpaymentsTheme {
        AddCardTopbar()
    }
}