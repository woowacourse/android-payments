package woowacourse.payments.ui.newcard

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
class NewCardContentsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_번호가_16자_미만이면_경고_메시지가_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("0".repeat(15))

        // then
        composeTestRule.onNodeWithText("카드 번호는 숫자 16자입니다.").assertIsDisplayed()
    }

    @Test
    fun 카드_번호를_16자_넘게_입력할_경우_첫_16자만_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("0".repeat(17))

        // then
        composeTestRule.onNodeWithText("0".repeat(16)).assertIsDisplayed()
    }

    @Test
    fun 카드_번호에_숫자가_아닌_문자가_있으면_경고_메시지가_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("abcdabcdabcdabcd")

        // then
        composeTestRule.onNodeWithText("카드 번호는 숫자 16자입니다.").assertIsDisplayed()
    }

    @Test
    fun 카드_번호를_입력_시_4자_단위로_기호를_삽입한다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("1234123412341234")

        // then
        composeTestRule.onNodeWithText("1234 - 1234 - 1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 만료일을_4자_넘게_입력할_경우_첫_4자만_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("01250")

        // then
        composeTestRule.onNodeWithText("0125").assertIsDisplayed()
    }

    @Test
    fun 만료일이_MM_YY_형식이_아니면_경고_메시지가_표시된다_1() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("abcd")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다.").assertIsDisplayed()
    }

    @Test
    fun 만료일이_MM_YY_형식이_아니면_경고_메시지가_표시된다_2() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("1325")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다.").assertIsDisplayed()
    }

    @Test
    fun 만료일이_이미_지났으면_경고_메시지가_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents(YearMonth.of(2025, 9)) }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("0825")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다.").assertIsDisplayed()
    }

    @Test
    fun 만료일을_입력_시_2자_단위로_기호를_삽입한다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("0925")

        // then
        composeTestRule.onNodeWithText("09 / 25").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름을_30자_넘게_입력할_경우_첫_30자만_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        target.performTextInput("0".repeat(31))

        // then
        composeTestRule.onNodeWithText("0".repeat(30)).assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름에_입력된_문자의_개수를_표시한다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        target.performTextInput("Hello World")

        // then
        composeTestRule.onNodeWithText("11 / 30").assertIsDisplayed()
    }

    @Test
    fun 비밀가_4자_미만이면_경고_메시지가_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("0")

        // then
        composeTestRule.onNodeWithText("비밀번호는 숫자 4자입니다.").assertIsDisplayed()
    }

    @Test
    fun 비밀번호를_4자_넘게_입력할_경우_첫_4자만_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("01234")

        // then
        composeTestRule.onNodeWithText("0123").assertIsDisplayed()
    }

    @Test
    fun 비밀번호에_숫자가_아닌_문자가_있으면_경고_메시지가_표시된다() {
        // given
        composeTestRule.setContent { NewCardContents() }
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("비밀번호")

        // when
        target.performTextInput("abcd")

        // then
        composeTestRule.onNodeWithText("비밀번호는 숫자 4자입니다.").assertIsDisplayed()
    }
}
