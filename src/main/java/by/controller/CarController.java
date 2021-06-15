package by.controller;

import by.inputModel.CarInputModel;
import by.exception.IncorrectDataException;
import by.model.Car;
import by.service.CarService;
import by.validator.CarValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class CarController {
    private final CarService carService;
    private final CarValidation carValidation;

    @Autowired
    public CarController(CarService carService, CarValidation carValidation) {
        this.carService = carService;
        this.carValidation = carValidation;
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> saveCar(@RequestBody(required = false) @Valid CarInputModel carInputModel,
                                       BindingResult bindingResult) {
        carValidation.requestCarValidation(carInputModel, bindingResult);
        return new ResponseEntity<>(carService.createAndSaveCar(carInputModel), HttpStatus.OK);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<Car> handleIncorrectDataException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
