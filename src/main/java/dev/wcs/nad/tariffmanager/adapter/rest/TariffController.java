package dev.wcs.nad.tariffmanager.adapter.rest;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.service.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TariffController {

    private final TariffService tariffService;

    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping("/api/tariffs")
    public ResponseEntity<Iterable<Tariff>> readTariffs() {
        return ResponseEntity.ok(tariffService.readAllTariffsWithPossibleOptions());
    }

    @GetMapping("/api/tariffs/{id}")
    public ResponseEntity<Optional<Tariff>> readTariff(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tariffService.readTariff(id));
    }

    @PutMapping(value = "/api/tariffs/{id}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Tariff>> updateTariff(@PathVariable("id") Long id, @RequestBody Tariff tariff) {
        Optional<Tariff> existingTariff = tariffService.readTariff(id);
        existingTariff.ifPresent(
            it -> {
                it.setPrice(tariff.getPrice());
                it.setName(tariff.getName());
                tariffService.updateTariff(it);
            }
        );
        return ResponseEntity.ok(existingTariff);
    }

    @PostMapping(value = "/api/tariffs", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Tariff> createNewTariff(@RequestBody Tariff tariff) {
        return ResponseEntity.ok(tariffService.createNewTariff(tariff));
    }

}
