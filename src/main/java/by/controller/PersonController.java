package by.controller;

import by.exception.NoPersonException;
import by.inputModel.PersonInputModel;
import by.exception.IncorrectDataException;
import by.model.Car;
import by.model.Person;
import by.service.PersonService;
import by.validator.PersonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class PersonController {
    private final PersonService personService;
    private final PersonValidation personValidation;

    @Autowired
    public PersonController(PersonService personService, PersonValidation personValidation) {
        this.personService = personService;
        this.personValidation = personValidation;
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody(required = false) @Valid PersonInputModel personInputModel,
                                             BindingResult bindingResult) throws ParseException {
        personValidation.requestPersonValidation(personInputModel, bindingResult);
        return new ResponseEntity<>(personService.createAndSavePerson(personInputModel), HttpStatus.OK);
    }

    @GetMapping(value = "/personWithCars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personWithCars(@RequestParam(value = "id", required = false) Long id) {
        personValidation.requestIdValidation(id);
        personValidation.requestExistPersonValidation(id);
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<Person> handleIncorrectDataException() { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

    @ExceptionHandler(NoPersonException.class)
    public ResponseEntity<Car> handleNoPersonException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
