package by.service;

import by.model.Person;

import java.util.List;

public interface PersonService {
    void saveOrUpdate(Person person);
    List<Person> findAll();
    Person findById(Long id);
    void deleteAll();
    Long count();
}
