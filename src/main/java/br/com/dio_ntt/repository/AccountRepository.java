package br.com.dio_ntt.repository;

import br.com.dio_ntt.exception.AccountNotFoundException;
import br.com.dio_ntt.exception.PixInUseException;
import br.com.dio_ntt.model.AccountWallet;

import java.util.ArrayList;
import java.util.List;

import static br.com.dio_ntt.repository.CommonsRepository.checkFundsForTransaction;

public class AccountRepository {

    private final List<AccountWallet> accounts = new ArrayList<>();

    public AccountWallet create(final List<String> pix, final long initialFunds) {
        if (!accounts.isEmpty()) {
            var pixInUse = accounts.stream().flatMap(a -> a.getPix().stream()).toList();
            for (var p : pix) {
                if (pixInUse.contains(p)) {
                    throw new PixInUseException("O pix '" + p + "'já está em uso");
                }
            }
        }
        var newAccount = new AccountWallet(initialFunds, pix);
        accounts.add(newAccount);
        return newAccount;
    }

    public void deposit(final String pix, final long fundsAmount) throws AccountNotFoundException {
        var target = findByPix(pix);
        target.addMoney(fundsAmount);
    }

    public void withdraw(final String pix, final long amount) throws AccountNotFoundException {
        var source = findByPix(pix);
        checkFundsForTransaction(source, amount);
        source.reduceMoney(amount);
    }

    public void transferMoney(final String sourcePix, final String targetPix, final long amount) throws AccountNotFoundException {
        var source = findByPix(sourcePix);
        checkFundsForTransaction(source, amount);
        var target = findByPix(targetPix);
        source.reduceMoney(amount);
        target.addMoney(amount);
    }

    public AccountWallet findByPix(final String pix) throws AccountNotFoundException {
        return accounts.stream()
                .filter(a -> a.getPix().contains(pix))
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("A conta com a chave pix '" + pix + "' não existe ou foi encerrada"));
    }

    public List<AccountWallet> list() {
        return this.accounts;
    }

}