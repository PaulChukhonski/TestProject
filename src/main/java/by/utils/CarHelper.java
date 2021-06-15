package by.utils;

import by.dto.CarDto;
import by.dto.PersonDto;
import by.model.Car;
import by.service.CarService;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CarHelper extends Helper {
    @Autowired
    public CarHelper(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    public boolean isCorrectData(CarDto carDto, BindingResult bindingResult) {
        return !bindingResult.hasErrors()
                && carDto != null
                && carService.findById(carDto.getId()) == null
                && personService.findById(carDto.getOwnerId()) != null
                && new Date().getTime() - personService.findById(carDto.getOwnerId()).getBirthdate().getTime() > 567_648_000_000L;
    }

    public Car createAndSaveCar(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        car.setHorsepower(carDto.getHorsepower());
        car.setPerson(personService.findById(carDto.getOwnerId()));
        carService.saveOrUpdate(car);
        return car;
    }
}

