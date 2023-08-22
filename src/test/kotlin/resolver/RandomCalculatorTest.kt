package resolver

import ParentCalculatorTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.TestInfo
import java.util.Random
import kotlin.test.assertEquals

/**
 * melakukan dependency injection di JUnit tanpa perlu membuat value baru
 * @RepeatedTest digunakan untuk test berulang
 * @ParameterizedTest untuk meng-inject parameter pada function
 */
class RandomCalculatorTest : ParentCalculatorTest() {

    @DisplayName("Test Loop Random Calculator")
    @RepeatedTest(
        value = 3,
        name = "{displayName} to {currentRepetition} from {totalRepetitions}"
    )
    fun testRandom(random: Random, testInfo: TestInfo){
        val value1 = random.nextInt(20)
        println(value1)
        val value2 = random.nextInt(20)
        println(value2)

        val result = calculator.add(value1, value2)

        assertEquals(value1 + value2, result)
    }

    @Disabled("library unresolved not found")
    @DisplayName("Test Calculator with Parameterized")
    fun testParamter(value: Int){
        val result = value + value
        assertEquals(result, calculator.add(value, value))
    }
}