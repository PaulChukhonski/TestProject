package by.utils;

import by.dto.PersonDto;
import by.model.Person;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PersonHelper extends Helper {
    private static final String timeFix = " 04:00:00";
    private static final String dateFormat = "dd.MM.yyyy";
    private static final String dateFormatForTimeFix = "dd.MM.yyyy HH:mm:ss";

    @Autowired
    public PersonHelper(PersonService personService) {
        this.personService = personService;
    }

    public boolean isCorrectData(PersonDto personDto, BindingResult bindingResult) throws ParseException {
        return !bindingResult.hasErrors()
                && personDto != null
                && personService.findById(personDto.getId()) == null
                && new Date().getTime() >= new SimpleDateFormat(dateFormat).parse(personDto.getBirthdate()).getTime();
    }

    public Person createAndSavePerson(PersonDto personDto) throws ParseException {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setBirthdate(new SimpleDateFormat(dateFormatForTimeFix).parse(personDto.getBirthdate() + timeFix));
        personService.saveOrUpdate(person);
        return person;
    }
}
