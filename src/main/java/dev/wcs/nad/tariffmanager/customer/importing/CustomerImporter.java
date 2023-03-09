package dev.wcs.nad.tariffmanager.customer.importing;

import dev.wcs.nad.tariffmanager.customer.model.*;
import dev.wcs.nad.tariffmanager.customer.reporting.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// We are using the logging abstraction from Lombok here with @Slf4j which byte-code injects a "log" Logger instance (see https://projectlombok.org/features/log)
@Slf4j
@Component
public class CustomerImporter {

    private final int juniorCustomerDiscountPercentage;
    private final int specialCustomerDiscountPercentage;

    public CustomerImporter(@Value("${junior.customer.discount.percent}") int juniorCustomerDiscountPercentage, @Value("${special.customer.discount.percent}") int specialCustomerDiscountPercentage) {
        this.juniorCustomerDiscountPercentage = juniorCustomerDiscountPercentage;
        this.specialCustomerDiscountPercentage = specialCustomerDiscountPercentage;
    }

    public List<Customer> importCustomers(File customerCsv) {
        List<String> customerLines;
        List<Customer> customers = new ArrayList<>();
        try {
            customerLines = Files.readAllLines(customerCsv.toPath());
            for (String customer : customerLines) {
                String[] temp = customer.split(",");
                String id = temp[0];
                String name = temp[1];
                String email = temp[2];
                String birthDay = temp[3];
                String lastBuy = temp[4];
                String type = temp[5];
                customers.add(parseCsvLineIntoObject(id, name, email, birthDay, lastBuy, type));
            }
        } catch (IOException e) {
            log.error("Could not read file.");
        }
        return customers;
    }

    private Customer parseCsvLineIntoObject(String id, String name, String email, String birthDay, String lastBuy, String type) {
        try {
            LocalDate birthDate = DateUtil.convertStringToLocalDate(birthDay);
            LocalDate lastBuyDate = DateUtil.convertStringToLocalDate(lastBuy);
            switch (type.toUpperCase()) {
                case "E": {
                    // Challenge: If you added a discount for this customer type, add the discount to the constructor here
                    SpecialCustomer specialCustomer = new SpecialCustomer(specialCustomerDiscountPercentage, id, name, email, birthDate, lastBuyDate);
                    return specialCustomer;
                }
                case "V": {
                    VICustomer viCustomer = new VICustomer(id, name, email, birthDate, lastBuyDate);
                    return viCustomer;
                }
                case "S": {
                    boolean youngerThan25 = Period.between(birthDate, LocalDate.now()).getYears() < 25;
                    boolean lastPurchaseIn25Days = Period.between(lastBuyDate, LocalDate.now()).getDays() < 90;
                    if (youngerThan25) {
                        JuniorCustomer juniorKunde = new JuniorCustomer(juniorCustomerDiscountPercentage, id, name, email, birthDate, lastBuyDate);
                        return juniorKunde;
                    } else if (lastPurchaseIn25Days) {
                        StandardCustomerWithPotential potentialCustomer = new StandardCustomerWithPotential(id, name, email, birthDate, lastBuyDate);
                        return potentialCustomer;
                    } else {
                        StandardCustomerNoPotential noPotentialCustomer = new StandardCustomerNoPotential(id, name, email, birthDate, lastBuyDate);
                        return noPotentialCustomer;
                    }
                }
            }
        } catch (DateTimeParseException e) {
            log.error("Cannot parse date for customer " + id + ".");
        }
        return null;
    }
}