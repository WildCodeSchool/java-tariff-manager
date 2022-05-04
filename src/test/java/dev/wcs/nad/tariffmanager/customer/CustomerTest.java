package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.model.SpecialCustomer;
import dev.wcs.nad.tariffmanager.customer.model.StandardCustomerNoPotential;
import dev.wcs.nad.tariffmanager.customer.model.VICustomer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void shouldCreateDifferentCustomersWithDifferentDiscounts() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        assertThat(alfred22.getEmail()).isEqualTo("alfred@web.de");
        assertThat(alfred22).isInstanceOf(VICustomer.class);
        assertThat(alfred22).isInstanceOf(Customer.class);

        assertThat(alfred22.calculateDiscountedPrice(100)).isEqualTo(90);
        assertThat(achim24.calculateDiscountedPrice(100)).isEqualTo(95);
        assertThat(egon54.calculateDiscountedPrice(100)).isEqualTo(100);
    }

    @Test
    public void shouldImplementFilterOnGenericPropertiesLikeName() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        // filter
    }

    @Test
    public void shouldImplementLogicOnBaseMethods() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        // filter
    }

}
