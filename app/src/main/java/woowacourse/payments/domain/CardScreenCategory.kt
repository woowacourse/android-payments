package woowacourse.payments.domain

import androidx.annotation.StringRes
import woowacourse.payments.R

enum class CardScreenCategory(
    @StringRes
    val topBarTitleId: Int,
) {
    Add(R.string.add_card_tool_bar_title),
    Edit(R.string.edit_card_tool_bar_title),
}
