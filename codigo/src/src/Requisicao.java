import java.time.LocalTime;
import java.util.ArrayList;


public class Requisicao {
    private int idRequisicao;
    private LocalTime entrada;
    private LocalTime saida;
    private Cliente cliente;
    private Mesa mesa;
    private boolean status;

    public Requisicao( int idRequisicao, LocalTime entrada, LocalTime saida, Cliente cliente, Mesa mesa, boolean status) {
        this.idRequisicao = idRequisicao;
        this.entrada = entrada;
        this.saida = saida;
        this.cliente = cliente;
        this.mesa = mesa;
        this.status = status;

    }
    public LocalTime getEntrada() {
        return entrada;
    }
    public int getIdRequisicao() {
        return idRequisicao;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public LocalTime getSaida() {
        return saida;
    }
    public void setDataHora(LocalTime entrada) {
        this.entrada = entrada;
    }
    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }
    public void setSaida() {
        this.saida = LocalTime.now();
        this.status = true;
    }
<<<<<<< Updated upstream


    public void encontrarMesa(){
        int convidados=this.cliente.getQntPessoas();
        if (convidados<= mesa.getCapacidade()) {
            mesa.ocuparMesa();
            this.setSaida();
        }

=======
    public int getConvidados() {
        return this.cliente.getQntPessoas();
>>>>>>> Stashed changes
    }



    public void alocarClienteEmMesa(Mesa m, ArrayList <Requisicao> requisicoes){
       m.ocuparMesa();
       this.setSaida();
    }


//END CLASS
}
