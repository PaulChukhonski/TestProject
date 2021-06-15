package by.service.impl;

import by.model.Car;
import by.model.Statistic;
import by.repository.CarRepository;
import by.repository.PersonRepository;
import by.repository.StatisticRepository;
import by.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final PersonRepository personRepository;
    private final CarRepository carRepository;
    private static final String vendorModelDelimiter = "-";

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository, PersonRepository personService, CarRepository carRepository) {
        this.statisticRepository = statisticRepository;
        this.personRepository = personService;
        this.carRepository = carRepository;
    }

    @Override
    public Statistic getStatistic() {
        Statistic statistic = new Statistic();
        List<String> vendors = new ArrayList<>();
        statistic.setPersonCount(personRepository.count());
        statistic.setCarCount(carRepository.count());
        for(Car car: carRepository.findAll()) {
            String vendor = car.getModel().split(vendorModelDelimiter)[0];
            if(!vendors.contains(vendor)) vendors.add(vendor);
        }
        statistic.setUniqueVendorCount((long) vendors.size());
        statisticRepository.save(statistic);
        return statistic;
    }
}
