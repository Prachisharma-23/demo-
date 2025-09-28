package study.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import study.demo.model.Growth;
import study.demo.repository.GrowthRepository;

@Service
public class GrowthService {

    private final GrowthRepository growthRepository;

    public GrowthService(GrowthRepository growthRepository) {
        this.growthRepository = growthRepository;
    }

    public List<Growth> getAllGrowth() {
        return growthRepository.findAll();
    }

    public Growth saveGrowth(Growth growth) {
        return growthRepository.save(growth);
    }
}
