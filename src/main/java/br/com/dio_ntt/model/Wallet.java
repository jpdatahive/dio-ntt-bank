package br.com.dio_ntt.model;

import lombok.Getter;

public abstract class Wallet {

    @Getter
    private final BankService service;

    protected long funds;

    public Wallet(final BankService serviceType) {
        this.service = serviceType;
        this.funds = 0;
    }

    public long getFunds() {
        return this.funds;
    }

    public void addMoney(final long amount) {
        this.funds += amount;
    }

    public void reduceMoney(final long amount) {
        this.funds -= amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "service=" + service +
                ", funds= R$" + funds / 100 + "," + funds % 100 +
                '}';
    }
}
