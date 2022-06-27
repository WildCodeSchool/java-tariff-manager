package dev.wcs.nad.tariffmanager.customer.collectiontesting;

import java.math.BigDecimal;

public interface Calculator {

    /**
     * calculate based on the input parameter
     */
    BigDecimal calculate(BigDecimal input);

    /**
     * reset the calculator
     */
    void reset();

    /**
     * is the calculator ready for calculations?
     */
    boolean isReady();

}
