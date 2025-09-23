package woowacourse.payments.ui.addcard.component

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.showToast

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddCardTopbar(
    isAddCardEnabled: Boolean = false,
    onAddCardSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.payments_addcard_topbar_add_card))
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.payments_topbar_back_icon_description),
                )
            }
        },
        actions = {
            IconButton(onClick = {
                if (isAddCardEnabled) {
                    onAddCardSuccess()
                } else {
                    context.showToast(context.getString(R.string.addcard_failed_to_add_card))
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.payments_topbar_checked_icon_description),
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun AddCardTopbarPreView() {
    AndroidpaymentsTheme {
        AddCardTopbar()
    }
}
