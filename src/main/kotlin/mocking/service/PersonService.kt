package mocking.service

import mocking.model.Person
import mocking.repository.PersonRepository

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
}