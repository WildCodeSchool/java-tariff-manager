package dev.wcs.nad.tariffmanager.cucumber;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import io.cucumber.java.fr.*;
public class TariffSteps {

    @Autowired
    TariffRepository tariffRepository;

    @Etantdonné("le(s) tariff(s) suivant(s):")
    public void givenTariffs(List<List<String>> data) {
        System.out.println("tariffsData: " + data);

        for (List<String> tariffData : data) {
            var tariff = new Tariff();
            tariff.setName(tariffData.get(0));
            tariff.setPrice(new BigDecimal( tariffData.get(1)));
            tariffRepository.saveAndFlush(tariff);
        }
    }
}
