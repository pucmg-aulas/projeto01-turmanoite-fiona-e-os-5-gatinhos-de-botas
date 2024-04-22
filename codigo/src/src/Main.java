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
        System.out.println("|================================|");
        System.out.print("   Escolha uma opção: \n");
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
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    } while (opcao != 3);

}
}