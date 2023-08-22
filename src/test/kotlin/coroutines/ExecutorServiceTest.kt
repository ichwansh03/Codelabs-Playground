package coroutines

import org.junit.jupiter.api.Test
import java.util.Date
import java.util.concurrent.Executors

/**
 * menggunakan Threadpool Executor untuk menjalankan banyak thread
 * SingleThreadPool menampung semua thread kemudian eksekusi satu per satu
 * FixedThreadPool menampung sebanyak n thread dan akan dieksekusi berbarengan sejumlah n thread
 */
class ExecutorServiceTest {

    @Test
    fun testSingleThreadPoolExecutor(){
        val executorService = Executors.newSingleThreadExecutor()
        repeat(5){
            val runnable = Runnable {
                Thread.sleep(1000)
                println("done $it ${Thread.currentThread().name} in ${Date()}")
            }

            executorService.execute(runnable)
            println("finished input thread single $it")
        }

        println("processed thread...")
        Thread.sleep(6000)
        println("done")
    }

    @Test
    fun testFixedThreadPoolExecutor(){
        val executorService = Executors.newFixedThreadPool(3)
        repeat(5){
            val runnable = Runnable {
                Thread.sleep(1000)
                println("done $it ${Thread.currentThread().name} in ${Date()}")
            }

            executorService.execute(runnable)
            println("finished input thread fixed $it")
        }

        println("processed thread...")
        Thread.sleep(6000)
        println("done")
    }
}