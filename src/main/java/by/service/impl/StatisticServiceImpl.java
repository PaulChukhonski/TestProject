package by.service.impl;

import by.model.Statistic;
import by.repository.StatisticRepository;
import by.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticServiceImpl(StatisticRepository statisticRepository) { this.statisticRepository = statisticRepository; }

    @Transactional
    @Override
    public void saveOrUpdate(Statistic statistic) {
        statisticRepository.save(statistic);
    }
}
