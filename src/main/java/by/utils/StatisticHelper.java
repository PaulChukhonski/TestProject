package by.utils;

import by.model.Car;
import by.model.Statistic;
import by.service.CarService;
import by.service.PersonService;
import by.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticHelper extends Helper {
    private static final String vendorModelDelimiter = "-";

    @Autowired
    public StatisticHelper(PersonService personService, CarService carService, StatisticService statisticService) {
        this.personService = personService;
        this.carService = carService;
        this.statisticService = statisticService;
    }

    public Statistic getStatistic() {
        Statistic statistic = new Statistic();
        List<String> vendors = new ArrayList<>();
        statistic.setPersonCount(personService.count());
        statistic.setCarCount(carService.count());
        for(Car car: carService.findAll()) {
            String vendor = car.getModel().split(vendorModelDelimiter)[0];
            if(!vendors.contains(vendor)) vendors.add(vendor);
        }
        statistic.setUniqueVendorCount((long) vendors.size());
        statisticService.saveOrUpdate(statistic);
        return statistic;
    }
}
