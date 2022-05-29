package dev.wcs.nad.tariffmanager.adapter.rest;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.service.TariffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
