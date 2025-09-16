package woowacourse.payments.ui.cardwallet.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme

@Composable
fun EmptyGuide() {
    Text(text = stringResource(R.string.new_card_guide))
}

@Preview(showBackground = true)
@Composable
private fun EmptyGuidePreview() {
    AndroidpaymentsTheme {
        EmptyGuide()
    }
}
