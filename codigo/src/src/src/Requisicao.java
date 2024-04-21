package src;
import java.time.LocalTime;
import java.util.ArrayList;


public class Requisicao {
    private int idRequisicao;
    private LocalTime entrada;
    private LocalTime saida;
    private Cliente cliente;
    private Mesa mesareq;
    private boolean status;

    public Requisicao( int idRequisicao, Cliente cliente, Mesa mesa) {
        this.idRequisicao = idRequisicao;
        this.entrada = LocalTime.now();
        this.saida = null;
        this.cliente = cliente;
        this.status = false;

    }
    public int getConvidados(){
        return this.cliente.getQntPessoas();
    }

    public LocalTime getEntrada() {
        return entrada;
    }
    public int getIdRequisicao() {
        return idRequisicao;
    }
    public Mesa getMesa() {
        return mesareq;
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


    public void encontrarMesa(){
        int convidados=this.cliente.getQntPessoas();
        for (Mesa mesa : Restaurante.mesas) {
            if (convidados<= mesa.getCapacidade()) {
                mesa.ocuparMesa();
                this.setSaida();
                mesareq = mesa;
                alocarClienteEmMesa(mesa, null);
            }
        }
        // mandar pra lista de espera, não sei como faz
    }



    public void alocarClienteEmMesa(Mesa m, ArrayList <Requisicao> requisicoes){
       m.ocuparMesa();
       this.setSaida();
    }


//END CLASS
}