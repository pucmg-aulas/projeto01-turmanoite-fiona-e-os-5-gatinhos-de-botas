import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Restaurante restaurante = new Restaurante();

        // Menu
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("\n\n\n\n\n");
            System.out.println("============== MENU ==============");
            System.out.println("|   1. Fazer requisição          |");
            System.out.println("|   2. Sair da mesa              |");
            System.out.println("|   3. Sair do programa          |");
            System.out.println("|   4. Consultar fila de espera  |");
            System.out.println("|================================|");
            System.out.print("   Escolha uma opção: \n");
            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        restaurante.fazerRequisicao();
                        break;
                    case 2:
                        restaurante.sairDaMesa();
                        break;
                    case 3:
                        System.out.println("Saindo do programa...");
                        break;
                    case 4:
                        System.out.println("Consultando fila...\n");
                        restaurante.statsFila();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next();
                opcao = 0;
            }
        } while (opcao != 3);

    }
}