import java.time.LocalTime;
import java.util.Scanner;

public class ControllerPrincipal {
    

    public Requisicao fzrRequisicao(String nomeCliente,int convidados){

        Cliente c = new Cliente(nomeCliente, convidados);
        Requisicao r = new Requisicao(c);

        return r;
    };


    // public void fazerRequisicao() {
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.println("Digite o nome do cliente: ");
    //     String nomeCliente = scanner.nextLine();

    //     System.out.println("Digite a quantidade de pessoas no total: ");
    //     int qntPessoas = scanner.nextInt();

    //     Cliente cliente = new Cliente(nomeCliente, qntPessoas);

    //     Requisicao r = new Requisicao(cliente);
    //     this.entrada = LocalTime.now();
    //     System.out.println("Requisicao feita!");
    //     requisicoes.add(r);
    //     this.imprimeRequisicao(r);

    //     r.setMesa(encontrarMesa(r));
    //     if (r.getMesa() == null && r.getConvidados() > 8) {
    //         System.out.println("Porém, não temos mesa para mais de 8 pessoas, desculpe.");
    //     } else if (r.getMesa() == null) {
    //         colocaFilaDeEspera(r);
    //     }
    // }
}
