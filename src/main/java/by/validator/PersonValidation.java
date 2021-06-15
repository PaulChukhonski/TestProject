package by.validator;

import by.exception.NoPersonException;
import by.inputModel.PersonInputModel;
import by.exception.IncorrectDataException;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PersonValidation {
    private static final String dateFormat = "dd.MM.yyyy";
    private final PersonService personService;

    @Autowired
    public PersonValidation(PersonService personService) {
        this.personService = personService;
    }

    public void requestPersonValidation(PersonInputModel personInputModel, BindingResult bindingResult) throws ParseException {
        if(isIncorrectData(personInputModel, bindingResult)) { throw new IncorrectDataException(); }
    }

    private boolean isIncorrectData(PersonInputModel personInputModel, BindingResult bindingResult) throws ParseException {
        return bindingResult.hasErrors()
                || personInputModel == null
                || personService.findById(personInputModel.getId()) != null
                || new Date().getTime() < new SimpleDateFormat(dateFormat).parse(personInputModel.getBirthdate()).getTime();
    }

    public void requestIdValidation(Long id) {
        if(isIncorrectId(id)) { throw new IncorrectDataException(); }
    }

    public void requestExistPersonValidation(Long id) {
        if(isNotExistsPerson(id)) { throw new NoPersonException(); }
    }

    private boolean isIncorrectId(Long id) {
        return id == null;
    }

    private boolean isNotExistsPerson(Long id) {
        return personService.findById(id) == null
                || personService.findById(id).getCars() == null
                || personService.findById(id).getCars().size() == 0;
    }
}
