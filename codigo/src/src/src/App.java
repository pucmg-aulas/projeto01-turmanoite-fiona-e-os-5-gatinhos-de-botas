package src;
import java.util.Scanner;


public class App {
    
public static void main(String[] args) throws Exception {


    // Menu
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
        System.out.println("\n\n\n\n\n"); // Espaços em branco para centralizar o menu
        System.out.println("============== MENU ==============");
        System.out.println("|   1. Fazer requisição          |");
        System.out.println("|   2. Sair da mesa              |");
        System.out.println("|   3. Sair do programa          |");
        System.out.println("|================================|");
        System.out.print("   Escolha uma opção: ");
        opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Por favor, insira o nome do cliente: ");
                String J = scanner.nextLine();
                System.out.println("Por favor, insira a quantidade de pessoas: ");
                int K = scanner.nextInt();
                Cliente c = new Cliente( J, K);
                Requisicao r = new Requisicao(opcao, c, null);
                r.encontrarMesa();
                break;
            case 2:
                System.out.println("Me informe o número da mesa: ");	
                int saida = scanner.nextInt();
                for (Requisicao requisicao : Restaurante.requisicoes) {
                    if (requisicao.getMesa().getnumero() == saida) {
                        System.out.println("Mesa desocupada com sucesso, obrigado pela preferência!");
                        requisicao.getMesa().desocuparMesa();
                    }
                }
                break;
            case 3:
                System.out.println("Saindo do programa...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcao != 3);

    scanner.close();
}
}