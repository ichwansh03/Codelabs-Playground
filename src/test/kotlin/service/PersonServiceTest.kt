package service

import mocking.model.Person
import mocking.repository.PersonRepository
import mocking.service.PersonService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * function test meng-cover semua kondisi dari class service
 */
@Extensions(value = [
    ExtendWith(MockitoExtension::class)
])
class PersonServiceTest {

    @Mock
    lateinit var personRepository: PersonRepository

    private lateinit var personService: PersonService

    @BeforeEach
    fun beforeEach(){
        personService = PersonService(personRepository)
    }

    @Test
    fun testPersonIdIsNotValid(){
        assertThrows<IllegalArgumentException> { personService.get("  ") }
    }

    @Disabled
    @Test
    fun testPersonNotFound(){
        assertThrows<IllegalArgumentException> { personService.get("person not found") }
    }

    @Test
    fun testPersonSuccess(){
        //tambahkan behaviour untuk assertEquals
        Mockito.`when`(personRepository.selectById("P001")).thenReturn(Person("P001","Ichwan"))

        val person = personService.get("P001")
        assertEquals("P001", person.id)
        assertEquals("Ichwan", person.name)
    }

    @Test
    fun testRegisterPersonNameIsBlank(){
        assertThrows<IllegalArgumentException> {
            personService.register("  ")
        }
    }

    @Test
    fun testRegisterSuccess() {
        val person = personService.register("Ichwan")

        assertEquals("Ichwan", person.name)
        assertNotNull(person.id)

        /**
         * melakukan verifikasi agar ketika call function insert() maka test akan gagal
         * karena ketika tidak call insert di productService maka test akan tetap jalan
         * diusahakan ketika membuat function yg return valuenya Unit maka harus diverfikasi
         */
        Mockito.verify(personRepository, Mockito.times(1)).insert(Person(person.id, "Ichwan"))
    }
}