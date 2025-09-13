package woowacourse.payments.ui.util

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule

fun ComposeContentTestRule.onNodeWithRoleAndContentDescription(
    role: Role,
    contentDescription: String,
) = onNode(
    SemanticsMatcher.expectValue(SemanticsProperties.Role, role)
        and
        hasContentDescription(contentDescription),
)
