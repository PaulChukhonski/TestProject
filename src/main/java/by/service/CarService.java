package by.service;

import by.inputModel.CarInputModel;
import by.model.Car;

public interface CarService {
    Car findById(Long id);
    void deleteAll();
    Car createAndSaveCar(CarInputModel carInputModel);
}
