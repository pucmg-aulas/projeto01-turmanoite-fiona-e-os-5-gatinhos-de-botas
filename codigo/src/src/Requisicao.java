import java.time.LocalTime;
import java.util.Date;

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
    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }
    public void encontrarMesa(){
        
    }

    public void alocarClienteEmMesa(Mesa m){
       m.ocuparMesa();
       this.setSaida(LocalTime.now());
    }

//END CLASS
}
