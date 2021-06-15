package by.service.impl;

import by.inputModel.CarInputModel;
import by.model.Car;
import by.repository.CarRepository;
import by.repository.PersonRepository;
import by.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PersonRepository personRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PersonRepository personRepository) {
        this.carRepository = carRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Car findById(Long id) { return carRepository.findById(id).orElse(null); }

    @Override
    public void deleteAll() { carRepository.deleteAll(); }

    @Override
    public Car createAndSaveCar(CarInputModel carInputModel) {
        Car car = new Car();
        car.setId(carInputModel.getId());
        car.setModel(carInputModel.getModel());
        car.setHorsepower(carInputModel.getHorsepower());
        car.setPerson(personRepository.findById(carInputModel.getOwnerId()).orElse(null));
        carRepository.save(car);
        return car;
    }
}
