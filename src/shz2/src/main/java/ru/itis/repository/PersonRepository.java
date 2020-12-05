package ru.itis.repository;

import ru.itis.model.Person;

public interface PersonRepository {
    Person getPersonById(long id);
    boolean deletePersonById(long id);
    boolean updatePersonById(long id, Person person);
    boolean savePerson(Person person);
}
