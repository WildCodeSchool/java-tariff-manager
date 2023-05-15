package dev.wcs.nad.tariffmanager.cucumber;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import dev.wcs.nad.tariffmanager.DBInitializer;
import dev.wcs.nad.tariffmanager.service.ContractService;
import dev.wcs.nad.tariffmanager.service.CustomerService;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@DataJpaTest
@Import({ ContractService.class, CustomerService.class, ParamHelper.class })
public class CucumberSpringConfiguration {

    @MockBean DBInitializer dbInitializer;

}