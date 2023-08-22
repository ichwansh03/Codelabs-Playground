package mocking.repository

import mocking.model.Person

/**
 * class contract dari data Person ke db
 */
interface PersonRepository {

    fun insert(person: Person)

    fun selectById(id: String): Person?
}