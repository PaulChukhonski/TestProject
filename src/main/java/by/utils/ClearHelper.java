package by.utils;

import by.service.CarService;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClearHelper extends Helper {
    @Autowired
    public ClearHelper(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    public void clearCarsAndPersons() {
        clearCars();
        clearPersons();
    }

    public void clearCars() { carService.deleteAll(); }

    public void clearPersons() { personService.deleteAll(); }
}
