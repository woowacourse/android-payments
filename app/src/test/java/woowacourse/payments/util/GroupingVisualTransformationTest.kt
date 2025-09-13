package woowacourse.payments.util

import androidx.compose.ui.text.AnnotatedString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.payments.ui.GroupingVisualTransformation

class GroupingVisualTransformationTest {
    private lateinit var groupingVisualTransformation: GroupingVisualTransformation

    @BeforeEach
    fun setUp() {
        groupingVisualTransformation =
            GroupingVisualTransformation(
                groupSize = 4,
                separator = "-",
            )
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "1", "12", "123"])
    fun `원본_텍스트_길이가_groupSize보다_작을_경우_그대로_반환된다`(value: String) {
        // given
        val original = AnnotatedString(value)

        // when
        val actual: String = groupingVisualTransformation.filter(original).text.text

        // then
        assertThat(actual).isEqualTo(value)
    }

    @Test
    fun `원본_텍스트_길이가_groupSize와_같을_경우_그대로_반환된다`() {
        // given
        val original = AnnotatedString("1234")

        // when
        val actual: String = groupingVisualTransformation.filter(original).text.text

        // then
        assertThat(actual).isEqualTo("1234")
    }

    @Test
    fun `원본_텍스트_길이가_groupSize를_넘을_경우separator가_표시된다`() {
        // given
        val original = AnnotatedString("12345")

        // when
        val actual: String = groupingVisualTransformation.filter(original).text.text

        // then
        assertThat(actual).isEqualTo("1234-5")
    }
}
