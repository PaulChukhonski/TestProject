package by.service.impl;

import by.model.Person;
import by.repository.PersonRepository;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    @Override
    public void saveOrUpdate(Person person) {
        personRepository.save(person);
    }

    @Transactional
    @Override
    public List<Person> findAll() { return personRepository.findAll(); }

    @Transactional
    @Override
    public Person findById(Long id) { return personRepository.findById(id).orElse(null); }

    @Transactional
    @Override
    public void deleteAll() { personRepository.deleteAll(); }

    @Transactional
    @Override
    public Long count() { return personRepository.count(); }
}
