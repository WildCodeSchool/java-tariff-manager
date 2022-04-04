package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import org.springframework.stereotype.Service;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public Iterable<Tariff> readAllTariffsWithPossibleOptions() {
        return tariffRepository.findAll();
    }
}
