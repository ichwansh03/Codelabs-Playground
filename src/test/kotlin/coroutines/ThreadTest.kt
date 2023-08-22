package coroutines

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.concurrent.thread

/**
 * membuat thread baru menggunakan interface Runnable (default java)
 * function thread() (default kotlin)
 * tidak disarankan membuat Thread secara manual
 */
class ThreadTest {

    @Test
    fun testNewThread(){
//        val runnable = Runnable {
//            println(Date())
//            Thread.sleep(2000)
//            println("finish (new thread): ${Date()}")
//        }
//
//        val thread = Thread(runnable)
//        thread.start()

        thread(start = true) {
            println(Date())
            Thread.sleep(2000)
            println("finish (${Thread.currentThread().name}): ${Date()}")
        }

        println("main thread run")
        Thread.sleep(3000)
        println("finish on main thread: ${Date()}")
    }
}