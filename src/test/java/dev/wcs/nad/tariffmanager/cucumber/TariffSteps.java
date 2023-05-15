package dev.wcs.nad.tariffmanager.cucumber;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import io.cucumber.java.fr.Etantdonné;
public class TariffSteps {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    OptionRepository optionRepository;

    @Etantdonné("le(s) tariff(s) suivant(s):")
    public void givenTariffs(List<List<String>> data) {
        System.out.println("tariffsData: " + data);

        for (List<String> tariffData : data) {
            var tariff = new Tariff();
            tariff.setName(tariffData.get(0));
            tariff.setPrice(new BigDecimal( tariffData.get(1)));
            
            // create default option
            var option = new Option();
            option.setName("option 1");
            option.setPrice(tariff.getPrice());
            optionRepository.save(option);
            tariff.setPossibleOptions(Set.of(option));

            tariffRepository.saveAndFlush(tariff);
        }
    }
}
