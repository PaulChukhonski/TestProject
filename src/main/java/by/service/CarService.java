package by.service;

import by.model.Car;

import java.util.List;

public interface CarService {
    void saveOrUpdate(Car car);
    List<Car> findAll();
    Car findById(Long id);
    void delete(Long id);
    Long count();
}
