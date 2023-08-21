import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

/**
 * annotation @Disabled untuk meng-ignored function test
 * @BeforeEach dan @AfterEach dieksekusi setiap function test dijalankan
 * ex: annotation Before dan After dapat digunakan untuk buka-tutup koneksi
 * jika ingin membatasi versi JRE, gunakan @EnabledForJreRange dan @DisabledForJreRange
 */

@DisplayName("Test from class Calculator")
class CalculatorTest{

    companion object {
        @BeforeAll
        @JvmStatic
        fun setupAll(){
            println("all unit test started")
        }

        @AfterAll
        @JvmStatic
        fun finishAll(){
            println("all unit test finished")
        }
    }

    private val calculator = Calculator()

    @BeforeEach
    fun setup(){
        println("before test begin")
    }

    @AfterEach
    fun finish(){
        println("finish after test")
    }

    @Test
    fun testAddValueSuccess(){
        val result = calculator.add(3,3)
        assertEquals(6, result)
    }

    @Disabled("test disabled")
    @Test
    fun testDivideValueSuccess(){
        val result = calculator.divide(10,5)
        assertEquals(2, result)
    }

    @Test
    fun testDivideValueFailed(){
        assertThrows<IllegalArgumentException>{
            calculator.divide(10,0)
        }
    }
}