# DIO & NTT-Data bank 

## Descrição do Projeto

Este projeto é uma simulação de um sistema bancário desenvolvido em Java, focado em demonstrar operações bancárias essenciais como criação de contas, depósitos, saques, transferências, gestão de investimentos e transações PIX. Ele foi estruturado para ser um exemplo claro de aplicação de conceitos de programação orientada a objetos (POO), tratamento de exceções e organização de código em um contexto de negócios.

## Funcionalidades

O sistema oferece as seguintes funcionalidades principais:

*   **Criação de Contas:** Permite a criação de novas contas bancárias.
*   **Depósito:** Realiza depósitos em contas existentes.
*   **Saque:** Permite saques de contas, com validação de saldo.
*   **Transferência:** Facilita a transferência de fundos entre contas.
*   **Gestão de Investimentos:**
    *   **Criação de Investimentos:** Permite que os usuários criem novos investimentos.
    *   **Resgate de Investimentos:** Funcionalidade para resgatar investimentos, com validação de saldo e regras de negócio.
*   **Consulta de Saldo:** Permite verificar o saldo atual de contas e investimentos.
*   **Extrato:** Geração de extratos detalhados para contas e investimentos.
*   **Transações PIX:** Simulação de transações PIX para transferências rápidas.
*   **Tratamento de Exceções:** Implementação robusta de tratamento de exceções para lidar com cenários como:
    *   `AccountNotFoundException`: Conta não encontrada.
    *   `AccountWithInvestmentException`: Tentativa de exclusão de conta com investimento ativo.
    *   `InvestmentNotFoundException`: Investimento não encontrado.
    *   `NoFundsEnoughException`: Saldo insuficiente para a operação.
    *   `PixInUseException`: Chave PIX já em uso.
    *   `WalletNotFoundException`: Carteira (conta ou investimento) não encontrada.

## Tecnologias Utilizadas

*   **Java 17+**: Linguagem de programação principal.
*   **Gradle**: Ferramenta de automação de build e gerenciamento de dependências.

## Estrutura do Projeto

O projeto segue uma estrutura de pacotes organizada para separar as responsabilidades:

*   **`Main.java`**: Contém o método `main` para iniciar a aplicação e demonstrar as funcionalidades do sistema bancário.
*   **`exception/`**: Define exceções personalizadas para tratar erros específicos do domínio bancário.
*   **`model/`**: Contém as classes que representam as entidades do negócio (e.g., `AccountWallet`, `Investment`, `Wallet`) e a lógica de serviço (`BankService`).
*   **`repository/`**: Contém as interfaces e implementações (atualmente em memória) para persistência de dados das contas e investimentos.

## Como Compilar e Executar

Para compilar e executar este projeto, você precisará ter o Java Development Kit (JDK) 17 ou superior instalado em sua máquina.

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/seu-usuario/dio-ntt-bank.git
    cd dio-ntt-bank
    ```

2.  **Compile o projeto usando Gradle:**
    ```bash
    ./gradlew build
    ```
    No Windows, use `gradlew.bat build`.

3.  **Execute a aplicação:**
    Após a compilação, você pode executar a classe principal. O Gradle pode criar um JAR executável ou você pode executar diretamente a classe `Main`.

    Para executar via Gradle (se configurado para tal):
    ```bash
    ./gradlew run
    ```
    Ou, para executar o JAR gerado (verifique o caminho exato em `build/libs`):
    ```bash
    java -jar build/libs/dio-ntt-bank-1.0-SNAPSHOT.jar # O nome do JAR pode variar
    ```

    Alternativamente, você pode executar diretamente a classe `Main` a partir do diretório raiz do projeto:
    ```bash
    java -cp build/classes/java/main br.com.dio_ntt.bank.Main
    ```

## Exemplo de Uso

O arquivo `Main.java` demonstra um fluxo de uso básico do sistema, incluindo a criação de contas, depósitos, saques, transferências e operações de investimento. Você pode modificar este arquivo para testar diferentes cenários e funcionalidades.

```java
// Exemplo simplificado de Main.java
public class Main {
    public static void main(String[] args) {
        BankService bank = new BankService();

        // Criar contas
        AccountWallet account1 = bank.createAccount("12345", "João Silva");
        AccountWallet account2 = bank.createAccount("67890", "Maria Oliveira");

        // Depositar
        bank.deposit(account1.getAccountNumber(), 1000.0);
        System.out.println("Saldo de João: " + bank.getAccountBalance(account1.getAccountNumber()));

        // Transferir
        try {
            bank.transfer(account1.getAccountNumber(), account2.getAccountNumber(), 200.0);
            System.out.println("Saldo de João após transferência: " + bank.getAccountBalance(account1.getAccountNumber()));
            System.out.println("Saldo de Maria após transferência: " + bank.getAccountBalance(account2.getAccountNumber()));
        } catch (NoFundsEnoughException e) {
            System.out.println("Erro na transferência: " + e.getMessage());
        }

        // Criar investimento
        Investment investment1 = bank.createInvestment(account1.getAccountNumber(), 500.0, "CDB");
        System.out.println("Saldo de investimento de João: " + bank.getInvestmentBalance(investment1.getInvestmentId()));

        // Resgatar investimento
        try {
            bank.redeemInvestment(investment1.getInvestmentId(), 300.0);
            System.out.println("Saldo de investimento de João após resgate: " + bank.getInvestmentBalance(investment1.getInvestmentId()));
        } catch (NoFundsEnoughException e) {
            System.out.println("Erro no resgate: " + e.getMessage());
        }
    }
}
```
