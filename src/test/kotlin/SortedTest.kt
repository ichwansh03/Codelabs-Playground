import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder

/**
 * @TestInstance membuat lifecycle method pada class agar berlanjut dan tidak independen
 * @Nested digunakan untuk membuat test didalam test
 */
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class)
class SortedTest {

    private var counter = 0

    @Test
    @Order(3)
    fun testA(){
        counter++
        println(counter)
    }

    @Test
    @Order(2)
    fun testB(){
        counter++
        println(counter)
    }

    @Test
    @Order(1)
    fun testC(){
        counter++
        println(counter)
    }
}