package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public Option createSelectableOption() {
        Option optionEntity = new Option();
        return optionRepository.save(optionEntity);
    }

    public Iterable<Option> loadAvailableOptions() {
        return optionRepository.findAll();
    }

}
