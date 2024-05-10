import java.util.ArrayList;

public class Pedido {
    private static ArrayList<Produto> produtos = new ArrayList<Produto>();
    private static double TAXA = 0.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;

    public Pedido() {
        this.idPedido = ++contadorPedidos;

    }

    public void adicionarProduto() {

    }

    public void calculaTotal() {

    }
}
