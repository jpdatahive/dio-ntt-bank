package br.com.dio_ntt;

import br.com.dio_ntt.exception.NoFundsEnoughException;
import br.com.dio_ntt.exception.PixInUseException;
import br.com.dio_ntt.repository.AccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        AccountRepository accountRepository = new AccountRepository();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("
Bem-vindo ao Dio NTT Bank!");
            System.out.println("1. Criar Conta");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Listar Contas");
            System.out.println("6. Ver Histórico da Conta");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Digite o valor inicial: ");
                        long initialFunds = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Digite as chaves pix (separadas por vírgula): ");
                        String pixKeysStr = scanner.nextLine();
                        var pixKeys = Arrays.stream(pixKeysStr.split(","))
                                .map(String::trim)
                                .collect(Collectors.toList());
                        var account = accountRepository.create(pixKeys, initialFunds);
                        System.out.println("Conta criada com sucesso: " + account);
                        break;
                    case 2:
                        System.out.print("Digite a chave pix para depositar: ");
                        String depositPix = scanner.nextLine();
                        System.out.print("Digite o valor para depositar: ");
                        long depositAmount = scanner.nextLong();
                        accountRepository.deposit(depositPix, depositAmount);
                        System.out.println("Depósito realizado com sucesso.");
                        break;
                    case 3:
                        System.out.print("Digite a chave pix para sacar: ");
                        String withdrawPix = scanner.nextLine();
                        System.out.print("Digite o valor para sacar: ");
                        long withdrawAmount = scanner.nextLong();
                        accountRepository.withdraw(withdrawPix, withdrawAmount);
                        System.out.println("Saque realizado com sucesso.");
                        break;
                    case 4:
                        System.out.print("Digite a chave pix de origem: ");
                        String sourcePix = scanner.nextLine();
                        System.out.print("Digite a chave pix de destino: ");
                        String targetPix = scanner.nextLine();
                        System.out.print("Digite o valor para transferir: ");
                        long transferAmount = scanner.nextLong();
                        accountRepository.transferMoney(sourcePix, targetPix, transferAmount);
                        System.out.println("Transferência realizada com sucesso.");
                        break;
                    case 5:
                        System.out.println("Contas: " + accountRepository.list());
                        break;
                    case 6:
                        System.out.print("Digite a chave pix para ver o histórico: ");
                        String historyPix = scanner.nextLine();
                        System.out.println("Histórico: " + accountRepository.getHistory(historyPix));
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (PixInUseException | NoFundsEnoughException | AccountNotFoundException e) {
                System.err.println("Erro: " + e.getMessage());
            }
        } else {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
