package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public ResponseEntity<Option> createSelectableOption() {
        Option optionEntity = new Option();
        return ResponseEntity.ok(optionRepository.save(optionEntity));
    }

}
