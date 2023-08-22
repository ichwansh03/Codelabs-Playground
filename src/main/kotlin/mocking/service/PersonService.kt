package mocking.service

import mocking.model.Person
import mocking.repository.PersonRepository
import java.util.UUID

/**
 * menyimpan business logic
 */
class PersonService(private val personRepository: PersonRepository) {

    fun get(id: String): Person {
        if (id.isBlank()){
            throw IllegalArgumentException("id not found")
        }

        val person = personRepository.selectById(id)
        if (person != null){
            return person
        } else {
            throw Exception("person not found")
        }
    }

    fun register(name: String): Person {
        if (name.isBlank()){
            throw IllegalArgumentException("name is blank")
        }

        val person = Person(UUID.randomUUID().toString(), name)

        personRepository.insert(person)

        return person
    }
}