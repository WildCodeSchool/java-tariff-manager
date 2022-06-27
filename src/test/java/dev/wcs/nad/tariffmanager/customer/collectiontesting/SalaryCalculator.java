package dev.wcs.nad.tariffmanager.customer.collectiontesting;

import java.math.BigDecimal;

public class SalaryCalculator implements Calculator {

    private final BigDecimal inflationRate;

    public SalaryCalculator(BigDecimal inflationRate) {
        this.inflationRate = inflationRate;
    }

    @Override
    public BigDecimal calculate(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(100).multiply(inflationRate));
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("SalaryCalculator cannot be reset.");
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
