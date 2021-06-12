package by.controller;

import by.model.Car;
import by.model.Person;
import by.model.Statistic;
import by.service.CarService;
import by.service.PersonService;
import by.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    private PersonService personService;
    @Autowired
    private CarService carService;
    @Autowired
    private StatisticService statisticService;;
    List<String> vendors = new ArrayList<>();

    @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person) {
        if (personService.findById(person.getId()) != null) { return new ResponseEntity<>(HttpStatus.CONFLICT); }
        personService.saveOrUpdate(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@Valid @RequestBody Car car, @RequestParam("ownerId") Long id) {
        car.setPerson(personService.findById(id));
        if (carService.findById(car.getId()) != null) { return new ResponseEntity<>(HttpStatus.CONFLICT); }
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
        Statistic statistic = new Statistic();
        statistic.setPersonCount(personService.count());
        statistic.setCarCount(carService.count());

        String vendor;
        for(Long id = 1L; id <= carService.count(); id++) {
            vendor = carService.findById(id).getModel().split("-")[0];
            if(!vendors.contains(vendor)) vendors.add(vendor);
        }

        statistic.setUniqueVendorCount((long) vendors.size());
        statisticService.saveOrUpdate(statistic);
        return new ResponseEntity<>(statistic, HttpStatus.OK);
    }

    @GetMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> deleteAllItems() {
        List<Person> persons = personService.findAll();
        if (persons == null) { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
        for(Person person: persons) {
            for(Car car: person.getCars()) {
                carService.delete(car.getId());
            }
            personService.delete(person.getId());
        }
        vendors.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}