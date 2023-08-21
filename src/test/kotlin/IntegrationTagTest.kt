import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

/**
 * mengatur include/exclude test di gradle dependencies
 */
@Tag("integration-test")
class IntegrationTagTest {

    @Test
    fun test(){
        println("test integration")
    }
}