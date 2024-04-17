import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    static ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
    static ArrayList<Requisicao> filaDeEspera = new ArrayList<Requisicao>();
    static ArrayList<Mesa> mesas = new ArrayList<Mesa>();

    public static Mesa encontrarMesa(Requisicao r) {
        int convidados = r.getConvidados();
        for (Mesa mesa : mesas) {
            if (convidados <= mesa.getCapacidade()) {
                mesa.ocuparMesa();
                return mesa;
            }
        }
        return null;
    }

    static public void fazerRequisicao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();

        System.out.println("Digite a quantidade de pessoas:");
        int qntPessoas = scanner.nextInt();

        Cliente cliente = new Cliente(idCliente, nomeCliente, qntPessoas);


        Mesa mesa = encontrarMesa(new Requisicao(0, null, null, cliente, null, false));
        if (mesa != null) {
            Requisicao requisicao = new Requisicao(0, null, null, cliente, mesa, false);
            requisicoes.add(requisicao);
            System.out.println("Requisição feita com sucesso.");
        } else {
            System.out.println("Não há mesas disponíveis para a quantidade de pessoas informada.");
        }

        scanner.close();
    }

    public static void main(String[] args) throws Exception {
        Mesa m1 = new Mesa(8, 1);
        Mesa m2 = new Mesa(8, 2);
        Mesa m3 = new Mesa(8, 3);
        Mesa m4 = new Mesa(8, 4);
        Mesa m5 = new Mesa(8, 5);
        Mesa m6 = new Mesa(8, 6);

        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);

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
                    fazerRequisicao();
                    break;
                case 2:
                    // sairDaMesa();
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
