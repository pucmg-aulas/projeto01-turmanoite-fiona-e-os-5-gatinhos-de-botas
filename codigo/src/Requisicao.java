
public class Requisicao {
    private static int contadorRequisicao = 0;
    private int idRequisicao;
    private Cliente cliente;
    private Mesa mesa;
    private boolean status;

    public Requisicao(Cliente cliente) {
        this.idRequisicao = ++contadorRequisicao;
        this.cliente = cliente;
        this.status = false;

    }

    public int getConvidados() {
        int convidados = this.cliente.getQtdPessoas();
        return convidados;
    }

    public int getIdRequisicao() {
        return idRequisicao;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public void setSaida() {
        this.status = true;
    }

    public void setMesa(Mesa m) {
        this.mesa = m;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    // END CLASS
}
