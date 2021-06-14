package by.service.impl;

import by.model.Car;
import by.repository.CarRepository;
import by.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) { this.carRepository = carRepository; }

    @Transactional
    @Override
    public void saveOrUpdate(Car car) { carRepository.save(car); }

    @Transactional
    @Override
    public List<Car> findAll() { return carRepository.findAll(); }

    @Transactional
    @Override
    public Car findById(Long id) { return carRepository.findById(id).orElse(null); }

    @Transactional
    @Override
    public void deleteAll() { carRepository.deleteAll(); }

    @Transactional
    @Override
    public Long count() { return carRepository.count(); }
}
