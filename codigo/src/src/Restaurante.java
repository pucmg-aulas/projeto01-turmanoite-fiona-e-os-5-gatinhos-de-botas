import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {
    static ArrayList<Requisicao> requisicoes = new ArrayList<Requisicao>();
    static ArrayList<Requisicao> filaDeEspera = new ArrayList<Requisicao>();
    static ArrayList<Mesa> mesas = new ArrayList<Mesa>();
//CONSTRUTORES 
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

        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.println("Digite a quantidade de pessoas no total: ");
        int qntPessoas = scanner.nextInt();

        Cliente cliente = new Cliente(nomeCliente, qntPessoas);

        Requisicao r = new Requisicao(cliente);
        System.out.println("Requisicao feita!");

        r.setMesa(encontrarMesa(r));
        if (r.getMesa() == null){
            colocaFilaDeEspera(r);
        }
        
        // scanner.close();
        // burrice do Cury, não pode fechar o scanner aqui
    }


//FILA DE ESPERA
    public void colocaFilaDeEspera(Requisicao requisicao){
        filaDeEspera.add(requisicao);
        System.out.println("Nao tem mesas disponiveis, você entrou na fila de espera, sua posição é a: " + filaDeEspera.size());
    }

    public void tirarFilaDeEspera(Requisicao requisicao){
        filaDeEspera.remove(requisicao);
    }

//SAIR DA MESA

    public void  sairDaMesa(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o número da mesa que deseja sair: ");
        int numero = scanner.nextInt();
        boolean mesaEncontrada = false;

        for(Mesa mesa : mesas){
            if(mesa.getnumero() == numero){
                mesa.desocuparMesa();
                this.verificarFila(mesa);
                mesaEncontrada = true; 
                break;
            }
        }
        if (!mesaEncontrada) {
            System.out.println("Mesa não encontrada.");
        }
    }


//VERIFICAR FILA
    public void verificarFila(Mesa m){
        for(Requisicao r : filaDeEspera){
            if(m.getCapacidade() >= r.getConvidados() && m.getStatus()==false){
                m.ocuparMesa();
                filaDeEspera.remove(r);
                System.out.println("Mesa ocupada pela fila de espera, Cliente: "+ r.getCliente().getNome());
            }
        }
    }


//end class
}
