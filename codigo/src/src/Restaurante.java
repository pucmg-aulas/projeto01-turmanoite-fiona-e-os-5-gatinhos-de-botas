import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    static ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
    static ArrayList<Requisicao> filaDeEspera = new ArrayList<Requisicao>();
    static ArrayList<Mesa> mesas = new ArrayList<Mesa>();
//CONSTRUTORES 
    static {
        Mesa m1 = new Mesa(8, 1);
        Mesa m2 = new Mesa(8, 2);
        Mesa m3 = new Mesa(8, 3);

        mesas.add(m1);
        mesas.add(m2);
        mesas.add(m3);
    }


//ENCONTRAR MESA
    public Mesa encontrarMesa(Requisicao r) {
        int convidados = r.getConvidados();
        for (Mesa mesa : mesas) {
            if (convidados <= mesa.getCapacidade() && mesa.getStatus()== false) {
                mesa.ocuparMesa();
                return mesa;
            }
        }
        return null;
    }

//FAZER REQUISICAO
    public void fazerRequisicao() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do cliente:");
        String nomeCliente = scanner.nextLine();

        System.out.println("Digite a quantidade de pessoas:");
        int qntPessoas = scanner.nextInt();

        Cliente cliente = new Cliente(nomeCliente, qntPessoas);

        Requisicao req = new Requisicao(cliente);

        encontrarMesa(req);
        
        scanner.close();
    }


//FILA DE ESPERA
    public void colocaFilaDeEspera(Requisicao requisicao){
        filaDeEspera.add(requisicao);
    }

    public void tirarFilaDeEspera(Requisicao requisicao){
        filaDeEspera.remove(requisicao);
    }

//SAIR DA MESA

    public void  sairDaMesa(){
        Scanner scanner = new Scanner(System.in);
        
    }

}
