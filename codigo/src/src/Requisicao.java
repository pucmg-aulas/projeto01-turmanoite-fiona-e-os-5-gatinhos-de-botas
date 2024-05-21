
public class Requisicao {
    private static int contadorRequisicao = 0;
    private int idRequisicao;
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido;
    private boolean status;

    public Requisicao(Cliente cliente) {
        this.idRequisicao = ++contadorRequisicao;
        this.cliente = cliente;
        this.status = false;
        this.pedido = new Pedido();

    }

    public boolean getStatus() {
        return (this.status);
    }

    public void imprimePedidos() {
        System.out.println("---------");
        System.out.println("Pedidos de :" + this.getCliente().getNome() + "(ID-" + this.idRequisicao + ")");
        for (Item_Pedido itempedido : this.pedido.getProdutos()) {
            System.out.println(itempedido.getQuantidade() + " - " + itempedido.getProduto().getNome() + " - R$ = " + itempedido.getQuantidade() + " x " + itempedido.getProduto().getPreço() + " = " + itempedido.getQuantidade() * itempedido.getProduto().getPreço());
        }
        System.out.println("---------");
    }

    public void reqAtiva() {
        this.status = true;
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

    public void reqInativa() {
        this.status = false;
    }

    public void setMesa(Mesa m) {
        this.mesa = m;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    // END CLASS
}