import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.concurrent.TimeUnit

/**
 * @Timeout membatasi timming test
 * @Execution digunakan untuk menjalankan test secara parallel (lihat config di junit-platform.properties)
 */
@Execution(ExecutionMode.CONCURRENT)
class TimmingTest {

    @Test
    @Timeout(value = 5, unit = TimeUnit.MINUTES)
    fun testTimeout(){
        Thread.sleep(3000L)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.MINUTES)
    fun testTimeout2(){
        Thread.sleep(3000L)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.MINUTES)
    fun testTimeout3(){
        Thread.sleep(3000L)
    }
}