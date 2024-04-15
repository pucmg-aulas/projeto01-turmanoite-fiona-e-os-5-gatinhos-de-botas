import java.util.Date;

public class Requisicao {
    private int idRequisicao;
    private Date dataHora;
    private Date saida;
    private Cliente cliente;
    private Mesa mesa;
    private boolean status;

    public Requisicao( int idRequisição, Date dataHora, Date saida, Cliente cliente, Mesa mesa, boolean status) {
        this.idRequisicao = idRequisição;
        this.dataHora = dataHora;
        this.saida = saida;
        this.cliente = cliente;
        this.mesa = mesa;
        this.status = status;
    }
    public Date getDataHora() {
        return dataHora;
    }
    public int getIdRequisicao() {
        return idRequisicao;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public Date getSaida() {
        return saida;
    }
    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
    public void setIdRequisicao(int idRequisição) {
        this.idRequisicao = idRequisição;
    }
    public void setSaida(Date saida) {
        this.saida = saida;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void encontrarMesa(){
        
    }
}
