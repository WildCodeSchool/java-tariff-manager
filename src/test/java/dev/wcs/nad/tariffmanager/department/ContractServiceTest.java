package dev.wcs.nad.tariffmanager.department;


import java.util.List;

import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.ContractRepository;
import dev.wcs.nad.tariffmanager.service.ContractService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    @MockBean
    private ContractRepository contractRepository;
    @Mock
    private Contract c1;
    @Mock
    private Contract c2;
    @Mock
    private Tariff tariff;

    @Test
    public void testReadContractsForUser() {

        when(tariff.getName()).thenReturn("My tariff");
        when(c1.getTariff()).thenReturn(tariff);
        when(contractRepository.findContractsByCustomer_IdIs(2L)).thenReturn(List.of(c1, c2));
        
        List<Contract> contracts = contractService.readContractsForUser(2L);
        
        assertEquals(2, contracts.size());
        assertEquals("My tariff", contracts.get(0).getTariff().getName());
    }
}
