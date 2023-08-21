import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import resolver.RandomParameterResolver

/**
 * melakukan pewarisan pada test
 */
@Extensions(value = [
    ExtendWith(RandomParameterResolver::class)
])
abstract class ParentCalculatorTest {

    val calculator = Calculator()

    @BeforeEach
    fun setupCalculator() {
        println("setup calculator")
    }
}