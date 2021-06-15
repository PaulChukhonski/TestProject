package by.controller;

import by.model.Statistic;
import by.service.CarService;
import by.service.PersonService;
import by.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private final PersonService personService;
    private final CarService carService;
    private final StatisticService statisticService;

    @Autowired
    public MainController(PersonService personService, CarService carService, StatisticService statisticService) {
        this.personService = personService;
        this.carService = carService;
        this.statisticService = statisticService;
    }

    @GetMapping(value = "/statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Statistic> getStatistic() {
        return new ResponseEntity<>(statisticService.getStatistic(), HttpStatus.OK);
    }

    @GetMapping(value = "/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCarsAndPersons() {
        carService.deleteAll();
        personService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}