package by.utils;

import by.dto.CarDto;
import by.model.Car;
import by.service.CarService;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Date;

@Service
public class CarHelper extends Helper {
    private static final Long minDriverAge = 567_648_000_000L;

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
                && new Date().getTime() - personService.findById(carDto.getOwnerId()).getBirthdate().getTime() >= minDriverAge;
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

