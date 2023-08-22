import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

/**
 * membuat test tiruan dari test yang sudah pernah dibuat
 * verify() untuk verifikasi berapa kali list dipanggil
 */
class MockTest {

    @Test
    fun testMock(){
        val list = Mockito.mock(List::class.java) as List<*>

        Mockito.`when`(list[0]).thenReturn("Ichwan")
        Mockito.`when`(list[1]).thenReturn("Sholihin")

        assertEquals("Ichwan", list[0])
        assertEquals("Ichwan", list[0])
        assertEquals("Sholihin", list[1])

        Mockito.verify(list, Mockito.times(2))[0]
    }
}