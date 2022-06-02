package dev.wcs.nad.tariffmanager.customer.reporting;

import dev.wcs.nad.tariffmanager.customer.model.*;
import dev.wcs.nad.tariffmanager.customer.reporting.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

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
public class CustomerImporter {

    private final int juniorCustomerDiscountPercentage;

    public CustomerImporter(int juniorCustomerDiscountPercentage) {
        this.juniorCustomerDiscountPercentage = juniorCustomerDiscountPercentage;
    }

    public List<Customer> importCustomers(File customerCsv) {
        List<String> customerLines;
        List<Customer> customers = new ArrayList<>();
        try {
            customerLines = Files.readAllLines(customerCsv.toPath());
            for (String customer : customerLines) {
                // Split line at "," and read information for object creation
                String[] temp = customer.split(",");
                String id = temp[0];
                String name = temp[1];
                String email = temp[2];
                String birthDay = temp[3];
                String lastBuy = temp[4];
                String type = temp[5];
                Customer marshalledCustomer = parseCsvLineIntoObject(id, name, email, birthDay, lastBuy, type);
                customers.add(marshalledCustomer);
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
                    // Create and return SpecialCustomer with values
                }
                case "V": {
                    // Create and return VICustomer with values
                }
                case "S": {
                    boolean youngerThan25 = Period.between(birthDate, LocalDate.now()).getYears() < 25;
                    boolean lastPurchaseIn25Days = Period.between(lastBuyDate, LocalDate.now()).getDays() < 90;
                    if (youngerThan25) {
                        // Create JuniorCustomer with juniorCustomerDiscountPercentage discount and return it
                    } else if (lastPurchaseIn25Days) {
                        // Create StandardCustomerWithPotential and return it
                    } else {
                        // Create StandardCustomerNoPotential and return it
                    }
                }
                default: {
                    throw new IllegalStateException("Cannot create object with type " + type.toUpperCase());
                }
            }
        } catch (DateTimeParseException e) {
            log.error("Cannot parse date for customer " + id + ".");
        }
        return null;
    }
}