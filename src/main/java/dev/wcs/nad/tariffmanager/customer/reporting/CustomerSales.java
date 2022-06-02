package dev.wcs.nad.tariffmanager.customer.reporting;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerSales {

    public static List<Customer> filterCustomersForType(List<Customer> customers, CustomerType type) {
        // Use Streams for Filtering
        List<Customer> customersOfType = customers.stream().filter(customer -> customer.getCustomerType() == type).collect(Collectors.toList());
        return customersOfType;
    }

    public static List<Customer> sortByName(List<Customer> customers) {
        List<Customer> mutableCustomerList = new ArrayList<>(customers);
        // with Comparator
        Collections.sort(mutableCustomerList, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        // with Lambdas
        mutableCustomerList.sort((customer1, customer2) -> customer1.getName().compareTo(customer2.getName()));
        // with Method References
        mutableCustomerList.sort(Comparator.comparing(Customer::getName));
        return mutableCustomerList;
    }

    public static List<Customer> sortByLastPurchase(List<Customer> customers) {
        List<Customer> mutableCustomerList = new ArrayList<>(customers);
        // with Lambdas
        mutableCustomerList.sort((customer1, customer2) -> customer1.getLastPurchase().compareTo(customer2.getLastPurchase()));
        // with Method References
        mutableCustomerList.sort(Comparator.comparing(Customer::getLastPurchase));
        return mutableCustomerList;
    }

}
