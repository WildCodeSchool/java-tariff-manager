package dev.wcs.nad.tariffmanager.adapter.rest;

import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/api/options")
    public ResponseEntity<Iterable<Option>> readOptions() {
        return ResponseEntity.ok(optionService.loadAvailableOptions());
    }

}
