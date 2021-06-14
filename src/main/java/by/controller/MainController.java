package by.controller;

import by.model.Car;
import by.model.Person;
import by.model.Statistic;
import by.service.CarService;
import by.service.PersonService;
import by.utils.ClearHelper;
import by.utils.StatisticHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@RestController
@Validated
public class MainController {
    private final PersonService personService;
    private final CarService carService;
    private final StatisticHelper statisticHelper;
    private final ClearHelper clearHelper;

    @Autowired
    public MainController(PersonService personService, CarService carService, StatisticHelper statisticHelper, ClearHelper clearHelper) {
        this.personService = personService;
        this.carService = carService;
        this.statisticHelper = statisticHelper;
        this.clearHelper = clearHelper;
    }

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@RequestBody @Valid Person person) {
        if (personService.findById(person.getId()) != null
                || new Date().getTime() < person.getBirthdate().getTime()) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        personService.saveOrUpdate(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@RequestBody @Valid Car car,
                                       @RequestParam("ownerId") @NotEmpty(message = "Id shouldn't be empty") Long id) {
        car.setPerson(personService.findById(id));
        if (carService.findById(car.getId()) != null
                || new Date().getTime() - car.getPerson().getBirthdate().getTime() <= 567648000000L
                || car.getHorsepower() <= 0
                || !car.getModel().matches(".-.")) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        carService.saveOrUpdate(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping(value = "/personWithCars", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personWithCars(@RequestParam("id") Long id) {
        if (id == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
        Person person = personService.findById(id);
        if (person == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
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