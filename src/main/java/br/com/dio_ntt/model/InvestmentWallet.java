package br.com.dio_ntt.model;

import lombok.Getter;

import static br.com.dio_ntt.model.BankService.INVESTMENT;

@Getter
public class InvestmentWallet extends Wallet {

    private final Investment investment;
    private final AccountWallet account;

    public InvestmentWallet(final Investment investment, final AccountWallet account, final long amount) {
        super(INVESTMENT);
        this.investment = investment;
        this.account = account;
        this.funds = amount;
    }

    public void updateAmount(final long percent) {
        var amount = getFunds() * percent / 100;
        addMoney(amount);
    }

    @Override
    public String toString() {
        return super.toString() + " InvestmentWallet{" +
                "investment=" + investment +
                ", account=" + account +
                '}';
    }
}