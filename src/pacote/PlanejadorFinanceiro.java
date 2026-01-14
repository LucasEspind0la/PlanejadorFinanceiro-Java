package pacote;

import java.util.ArrayList;
import java.util.Scanner;

public class PlanejadorFinanceiro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu salário mensal: R$ ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consome a quebra de linha pendente após nextDouble()

        ArrayList<String> nomesDespesas = new ArrayList<>();
        ArrayList<Double> valoresDespesas = new ArrayList<>();

        System.out.println("\nAgora, vamos cadastrar suas despesas:");

        while (true) {
            System.out.print("Digite o nome da despesa (ou 'sair' para finalizar): ");
            String nome = scanner.nextLine().trim();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            if (nome.isEmpty()) {
                System.out.println("Nome inválido. Tente novamente.");
                continue;
            }

            System.out.print("Digite o valor da despesa '" + nome + "': R$ ");
            double valor;
            try {
                valor = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
                continue;
            }

            if (valor < 0) {
                System.out.println("Valor não pode ser negativo. Ignorando...");
                continue;
            }

            if (valor == 0) {
                System.out.println("Despesa de R$ 0 ignorada.");
                continue;
            }

            nomesDespesas.add(nome);
            valoresDespesas.add(valor);

            // Calcula total atual
            double totalAtual = 0;
            for (double v : valoresDespesas) totalAtual += v;
            System.out.println("✅ Despesa adicionada! Total até agora: R$ " + String.format("%.2f", totalAtual));
        }

        // Calcula total final
        double totalDespesas = 0;
        for (double valor : valoresDespesas) {
            totalDespesas += valor;
        }

        double saldo = salario - totalDespesas;

        // Exibe relatório
        System.out.println("\n" + "=".repeat(40));
        System.out.println("        RESUMO FINANCEIRO");
        System.out.println("=".repeat(40));
        System.out.println("Salário: R$ " + String.format("%.2f", salario));

        if (nomesDespesas.isEmpty()) {
            System.out.println("\nNenhuma despesa registrada.");
        } else {
            System.out.println("\nDespesas cadastradas:");
            for (int i = 0; i < nomesDespesas.size(); i++) {
                System.out.println("  • " + nomesDespesas.get(i) + ": R$ " + String.format("%.2f", valoresDespesas.get(i)));
            }
        }

        System.out.println("\nTotal de despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo final/ Restante: R$ " + String.format("%.2f", saldo));

        System.out.println();
        if (saldo > 0) {
            System.out.println("🎉 Parabéns! Você economizou este mês.");
        } else if (saldo == 0) {
            System.out.println("⚠️ Você gastou exatamente tudo.");
        } else {
            System.out.println("💸 Cuidado! Seu saldo ficou negativo.");
        }

        scanner.close();
    }
}
