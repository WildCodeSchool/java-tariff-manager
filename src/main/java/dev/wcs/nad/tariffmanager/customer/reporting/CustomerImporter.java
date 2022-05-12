package dev.wcs.nad.tariffmanager.customer.reporting;

import dev.wcs.nad.tariffmanager.customer.model.*;
import dev.wcs.nad.tariffmanager.customer.reporting.util.DateUtil;
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

@Component
public class CustomerImporter {

    private final int juniorCustomerDiscountPercentage;

    public CustomerImporter(@Value("${junior.customer.discount.percent}") int juniorCustomerDiscountPercentage) {
        this.juniorCustomerDiscountPercentage = juniorCustomerDiscountPercentage;
    }

    /*
    IF (TYPE='E')
        NEW EXKLUSIVKUNDE
    ELSE IF (TYPE='V')
        NEW VIKUNDE
    ELSE IF (TYPE='S') AND (AGE < 25)
        NEW JUNIORKUNDE
    ELSE IF (TYPE='S') AND (LAST_PURCHASE < 90 DAYS)
        NEW STANDARDKUNDE_MIT_POTENTIAL
    ELSE
        NEW STANDARDKUNDE_OHNE_POTENTIAL
    XDF57FEO3VQ,Moses Finch,ipsum.ac@quamvel.co.uk,02.01.2000,01.08.2021,S
     */
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

                try {
                    LocalDate birthDate = DateUtil.convertStringToLocalDate(birthDay);
                    LocalDate lastBuyDate = DateUtil.convertStringToLocalDate(lastBuy);
                    switch (type) {
                        case "E": {
                            SpecialCustomer specialCustomer = new SpecialCustomer(id, name, email, birthDate, lastBuyDate);
                            customers.add(specialCustomer);
                        }
                        break;
                        case "V": {
                            VICustomer viCustomer = new VICustomer(id, name, email, birthDate, lastBuyDate);
                            customers.add(viCustomer);
                        }
                        break;
                        case "S": {
                            boolean youngerThan25 = Period.between(birthDate, LocalDate.now()).getYears() < 25;
                            boolean lastPurchaseIn25Days = Period.between(lastBuyDate, LocalDate.now()).getDays() < 90;
                            if (youngerThan25) {
                                JuniorCustomer juniorKunde = new JuniorCustomer(juniorCustomerDiscountPercentage, id, name, email, birthDate, lastBuyDate);
                                customers.add(juniorKunde);
                            } else if (lastPurchaseIn25Days) {
                                StandardCustomerWithPotential potentialCustomer = new StandardCustomerWithPotential(id, name, email, birthDate, lastBuyDate);
                                customers.add(potentialCustomer);
                            } else {
                                StandardCustomerNoPotential noPotentialCustomer = new StandardCustomerNoPotential(id, name, email, birthDate, lastBuyDate);
                                customers.add(noPotentialCustomer);
                            }
                        }
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.err.println("Cannot parse date for customer " + id + ".");
                }
            }
        } catch (IOException e) {
            System.err.println("Could not read file.");
        }
        return customers;
    }
}