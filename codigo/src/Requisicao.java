import java.time.LocalDateTime;

public class Requisicao {
    private static int idRequisicao;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private Cliente cliente;
    private Mesa mesa;
    private boolean status;

    public Requisicao(Cliente cliente){
        Requisicao.idRequisicao += 1;
        this.cliente = cliente;
        this.dataHoraEntrada = LocalDateTime.now();
        this.status = false; 
    }

    public void setSaida(){
        this.dataHoraSaida = LocalDateTime.now();
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setMesa(Mesa mesa){
        this.mesa = mesa;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

}
