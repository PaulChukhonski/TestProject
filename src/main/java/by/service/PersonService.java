package by.service;

import by.inputModel.PersonInputModel;
import by.model.Person;

import java.text.ParseException;

public interface PersonService {
    Person findById(Long id);
    void deleteAll();
    Person createAndSavePerson(PersonInputModel personInputModel) throws ParseException;
}
