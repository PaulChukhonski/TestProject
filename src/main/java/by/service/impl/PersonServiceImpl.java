package by.service.impl;

import by.inputModel.PersonInputModel;
import by.model.Person;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PersonServiceImpl implements PersonService {
    private final by.repository.PersonRepository personRepository;
    private static final String timeFix = " 04:00:00";
    private static final String dateFormatForTimeFix = "dd.MM.yyyy HH:mm:ss";

    @Autowired
    public PersonServiceImpl(by.repository.PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(Long id) { return personRepository.findById(id).orElse(null); }

    @Override
    public void deleteAll() { personRepository.deleteAll(); }

    @Override
    public Person createAndSavePerson(PersonInputModel personInputModel) throws ParseException {
        Person person = new Person();
        person.setId(personInputModel.getId());
        person.setName(personInputModel.getName());
        person.setBirthdate(new SimpleDateFormat(dateFormatForTimeFix).parse(personInputModel.getBirthdate() + timeFix));
        person.setCars(null);
        personRepository.save(person);
        return person;
    }
}
