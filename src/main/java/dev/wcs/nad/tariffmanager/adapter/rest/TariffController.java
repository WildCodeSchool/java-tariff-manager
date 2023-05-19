package dev.wcs.nad.tariffmanager.adapter.rest;

import static java.util.stream.StreamSupport.stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.tariff.TariffDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.service.TariffService;

@RestController
public class TariffController {

    private final TariffService tariffService;
    private EntityToDtoMapper mapper;

    public TariffController(TariffService tariffService, EntityToDtoMapper mapper) {
        this.tariffService = tariffService;
        this.mapper = mapper;
    }

    @GetMapping("/api/tariffs")
    public ResponseEntity<Iterable<TariffDto>> readTariffs() {
        var tariffs = stream(tariffService.readAllTariffsWithPossibleOptions().spliterator(), false).toList();
        return ResponseEntity.ok(mapper.tariffsToDto(tariffs));
    }

}
