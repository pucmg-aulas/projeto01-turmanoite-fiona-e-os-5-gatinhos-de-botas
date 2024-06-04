public class RequisicaoModel {
    
    
    private static int contadorRequisicao = 0;
    private int idRequisicao;
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido;
    private boolean status;

    public RequisicaoModel(Cliente cliente) {
        this.idRequisicao = ++contadorRequisicao;
        this.cliente = cliente;
        this.status = false;
        this.pedido = new Pedido();

    }
}
