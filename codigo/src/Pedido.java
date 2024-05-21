import java.util.ArrayList;

public class Pedido {
    private static ArrayList<Produto> produtos = new ArrayList<Produto>();
    private static final double TAXA = 0.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;


    public Pedido(double totalProdutos) {
        this.idPedido += 1;
        this.totalProdutos = totalProdutos;
    }


    public Pedido() {
        this.idPedido = ++contadorPedidos;

    }

    public void adicionarProduto() {

    }

    public void calculaTotal() {

    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getTotalProdutos() {
        return this.totalProdutos;
    }

    public void setTotalProdutos(double totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

}
