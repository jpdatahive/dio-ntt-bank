package br.com.dio_ntt.model;

import lombok.Getter;

import java.util.List;

import static br.com.dio_ntt.model.BankService.ACCOUNT;

@Getter
public class AccountWallet extends Wallet {

    private final List<String> pix;

    public AccountWallet(final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
    }

    public AccountWallet(final long initialFunds, final List<String> pix) {
        super(ACCOUNT);
        this.pix = pix;
        this.funds = initialFunds;
    }

    @Override
    public String toString() {
        return super.toString() + " AccountWallet{" +
                "pix=" + pix +
                '}';
    }
}
