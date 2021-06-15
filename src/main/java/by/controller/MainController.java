package by.controller;

import by.dto.CarDto;
import by.dto.PersonDto;
import by.model.Car;
import by.model.Person;
import by.model.Statistic;
import by.service.PersonService;
import by.utils.CarHelper;
import by.utils.ClearHelper;
import by.utils.PersonHelper;
import by.utils.StatisticHelper;
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
public class MainController {
    private final PersonService personService;
    private final StatisticHelper statisticHelper;
    private final ClearHelper clearHelper;
    private final PersonHelper personHelper;
    private final CarHelper carHelper;

    @Autowired
    public MainController(PersonService personService, StatisticHelper statisticHelper, ClearHelper clearHelper,
                          PersonHelper personHelper, CarHelper carHelper) {
        this.personService = personService;
        this.statisticHelper = statisticHelper;
        this.clearHelper = clearHelper;
        this.personHelper = personHelper;
        this.carHelper = carHelper;
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody(required = false) @Valid PersonDto personDto,
                                             BindingResult bindingResult) throws ParseException {
        if(!personHelper.isCorrectData(personDto, bindingResult)) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity<>(personHelper.createAndSavePerson(personDto), HttpStatus.OK);
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@RequestBody(required = false) @Valid CarDto carDto,
                                       BindingResult bindingResult) {
        if(!carHelper.isCorrectData(carDto, bindingResult)) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity<>(carHelper.createAndSaveCar(carDto), HttpStatus.OK);
    }

    @GetMapping(value = "/personWithCars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personWithCars(@RequestParam(value = "id", required = false) Long id) {
        if (id == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        Person person = personService.findById(id);
        if (person == null || person.getCars() == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Statistic> getStatistic() {
        return new ResponseEntity<>(statisticHelper.getStatistic(), HttpStatus.OK);
    }

    @GetMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCarsAndPersons() {
        clearHelper.clearCarsAndPersons();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}