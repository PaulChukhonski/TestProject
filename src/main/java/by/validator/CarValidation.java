package by.validator;

import by.inputModel.CarInputModel;
import by.exception.IncorrectDataException;
import by.service.CarService;
import by.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Date;

@Component
public class CarValidation {
    private static final Long minDriverAge = 567_648_000_000L;
    private final PersonService personService;
    private final CarService carService;

    @Autowired
    public CarValidation(PersonService personService, CarService carService) {
        this.personService = personService;
        this.carService = carService;
    }

    public void requestCarValidation(CarInputModel carInputModel, BindingResult bindingResult) {
        if(isIncorrectData(carInputModel, bindingResult)) { throw new IncorrectDataException(); }
    }

    public boolean isIncorrectData(CarInputModel carInputModel, BindingResult bindingResult) {
        return bindingResult.hasErrors()
                || carInputModel == null
                || carService.findById(carInputModel.getId()) != null
                || personService.findById(carInputModel.getOwnerId()) == null
                || new Date().getTime() - personService.findById(carInputModel.getOwnerId()).getBirthdate().getTime() < minDriverAge;
    }
}
