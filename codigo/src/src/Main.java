import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Screen screen = new Screen();
        screen.initialize();

        Restaurante restaurante = new Restaurante();

        // Menu
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // do {

        //     System.out.println("\n");
        //     System.out.println("============== MENU ==================");
        //     System.out.println("|   1. Fazer requisição              |");
        //     System.out.println("|   2. Sair da mesa                  |");
        //     System.out.println("|   3. Sair do programa              |");
        //     System.out.println("|   4. Consultar fila de espera      |");
        //     System.out.println("|   5. Cancelar requisição em espera |");
        //     System.out.println("|   6. Realizar um pedido            |");
        //     System.out.println("|   7. Finalizar um pedido           |");
        //     System.out.println("|   8. Consultar um pedido           |");
        //     System.out.println("|====================================|");
        //     System.out.print("   Escolha uma opção: \n");
        //     if (scanner.hasNextInt()) {
        //         opcao = scanner.nextInt();
        //         switch (opcao) {
        //             case 1:
        //                 restaurante.fazerRequisicao();
        //                 break;
        //             case 2:
        //                 restaurante.sairDaMesa();
        //                 break;
        //             case 3:
        //                 System.out.println("Saindo do programa...");
        //                 break;
        //             case 4:
        //                 System.out.println("Consultando fila...\n");
        //                 restaurante.statsFila();
        //                 break;
        //             case 5:
        //                 System.out.println("Consultando fila...\n");
        //                 restaurante.statsFila();
        //                 restaurante.cancelarRequisicao();
        //                 break;
        //             case 6:
        //                 System.out.println("Realizando pedido...\n");
        //                 restaurante.fazerPedido();
        //                 break;
        //             case 7:
        //                 System.out.println("Finalizando pedido...\n");
        //                 restaurante.fecharPedido();
        //                 break;
        //             case 8:
        //                 System.out.println("consultando...\n");
        //                 restaurante.consultarPedido();
        //                 break;
        //             default:
        //                 System.out.println("Opção inválida. Tente novamente.");
        //         }
        //     } else {
        //         System.out.println("Opção inválida. Tente novamente.");
        //         scanner.next();
        //         opcao = 0;
        //     }
        // } while (opcao != 3);
    }

}