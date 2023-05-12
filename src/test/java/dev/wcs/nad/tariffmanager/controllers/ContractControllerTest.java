package dev.wcs.nad.tariffmanager.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.wcs.nad.tariffmanager.DBInitializer;
import dev.wcs.nad.tariffmanager.adapter.rest.ContractController;
import dev.wcs.nad.tariffmanager.identity.config.PasswordEncoderConfig;
import dev.wcs.nad.tariffmanager.identity.config.WebSecurityConfig;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.service.ContractService;

@Import({ WebSecurityConfig.class, PasswordEncoderConfig.class })
@WebMvcTest(controllers = ContractController.class)
public class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;


    @Test
    public void testCreateContractForUser() throws Exception {
        Long userId = 1L;
        Long tariffId = 10L;
        Long optionId = 100L;
        Long customerId = 1000L;

        var body = Map.of(
                "tariffId", tariffId,
                "optionId", optionId,
                "customerId", customerId);

        String bodyJson = new ObjectMapper().writeValueAsString(body);
        mockMvc.perform(
                post("/api/contracts/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"status\": \"VALIDATED\" }"));
    }

    @MockBean
    DBInitializer dbInitializer;
    @MockBean
    EntityToDtoMapper entityToDtoMapper;
    @MockBean
    dev.wcs.nad.tariffmanager.identity.user.SecurityUserService securityUserService;

}
