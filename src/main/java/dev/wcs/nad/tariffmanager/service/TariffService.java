package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import org.springframework.stereotype.Service;

@Service
public class TariffService {

    private final TariffRepository tariffRepository;
    private final OptionRepository optionRepository;

    public TariffService(TariffRepository tariffRepository, OptionRepository optionRepository) {
        this.tariffRepository = tariffRepository;
        this.optionRepository = optionRepository;
    }

    public Iterable<Tariff> readAllTariffsWithPossibleOptions() {
        return tariffRepository.findAll();
    }

    public Tariff assignOptionToTariff(Long tariffId, Long optionId) {
        Tariff tariff = tariffRepository.findById(tariffId).get();
        Option option = optionRepository.findById(optionId).get();
        tariff.getPossibleOptions().add(option);
        return tariffRepository.save(tariff);
    }
}
