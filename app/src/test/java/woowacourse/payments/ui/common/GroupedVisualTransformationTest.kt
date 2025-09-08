package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GroupedVisualTransformationTest {
    @ParameterizedTest(name = "groupSize={0}, separator='{1}', input='{2}' => '{3}'")
    @MethodSource("provideCases")
    @DisplayName("다양한 groupSize, separator, 입력에 따른 포맷 검증")
    fun testFormatting(case: TestCase) {
        // given
        val transformation = GroupedVisualTransformation(case.groupSize, case.separator)
        val input = AnnotatedString(case.input)

        // when
        val result = transformation.filter(input)

        // then
        assertEquals(case.expected, result.text.text)
    }

    data class TestCase(
        val groupSize: Int,
        val separator: String,
        val input: String,
        val expected: String,
    )

    companion object {
        @JvmStatic
        fun provideCases(): Stream<TestCase> =
            Stream.of(
                // 빈 문자열
                TestCase(4, "-", "", ""),
                // groupSize보다 짧음
                TestCase(4, "-", "123", "123"),
                // groupSize와 같음
                TestCase(4, "-", "1234", "1234"),
                // groupSize 배수
                TestCase(3, "-", "123456", "123-456"),
                // groupSize 배수가 아님
                TestCase(3, "-", "1234567", "123-456-7"),
                // 단일 문자 separator
                TestCase(2, ":", "12345", "12:34:5"),
                // 다중 문자 separator
                TestCase(2, "--", "12345", "12--34--5"),
                // groupSize 1
                TestCase(1, ":", "123", "1:2:3"),
                // groupSize보다 큰 입력
                TestCase(10, "-", "12345", "12345"),
                // separator가 빈 문자열
                TestCase(3, "", "12345", "12345"),
            )
    }
}
