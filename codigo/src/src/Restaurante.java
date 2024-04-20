import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    static ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
    static ArrayList<Requisicao> filaDeEspera = new ArrayList<Requisicao>();
    static ArrayList<Mesa> mesas = new ArrayList<Mesa>();

    static {
        Mesa m1 = new Mesa(4, 1);
        Mesa m2 = new Mesa(4, 2);
        Mesa m3 = new Mesa(4, 3);
        Mesa m4 = new Mesa(4, 4);
        Mesa m5 = new Mesa(6, 5);
        Mesa m6 = new Mesa(6, 6);
        Mesa m7 = new Mesa(6, 7);
        Mesa m8 = new Mesa(6, 8);
        Mesa m9 = new Mesa(8, 9);
        Mesa m10 = new Mesa(8, 10);

        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
        mesas.add(m4);
        mesas.add(m5);
        mesas.add(m6);
        mesas.add(m7);
        mesas.add(m8);
        mesas.add(m9);
        mesas.add(m10);

    }

    public Mesa encontrarMesa(Requisicao r) {
        int convidados = r.getConvidados();
        for (Mesa mesa : mesas) {
            if (convidados <= mesa.getCapacidade()) {
                mesa.ocuparMesa();
                return mesa;
            }
        }
        return null;
    }

    public void fazerRequisicao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o ID do cliente:");
        int idCliente = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();

        System.out.println("Digite a quantidade de pessoas:");
        int qntPessoas = scanner.nextInt();

        Cliente cliente = new Cliente(idCliente, nomeCliente, qntPessoas);


        Mesa mesa = encontrarMesa(new Requisicao(qntPessoas, cliente, null));
        if (mesa != null) {
            Requisicao requisicao = new Requisicao(qntPessoas, cliente, mesa);
            requisicoes.add(requisicao);
            System.out.println("Requisição feita com sucesso.");
        } else {
            System.out.println("Não há mesas disponíveis para a quantidade de pessoas informada.");
        }

        scanner.close();
    }



}
