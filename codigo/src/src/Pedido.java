import java.util.ArrayList;

public class Pedido {
    private ArrayList<Item_Pedido> produtos = new ArrayList<Item_Pedido>();
    private static double TAXA = 0.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;
    
    public Pedido() {
        this.idPedido = ++contadorPedidos;
    }

    public ArrayList<Item_Pedido> getProdutos() {
        return produtos;
    }

    public void addProduto(Item_Pedido p) {
        produtos.add(p);
    }

    public void calculaTotal() {

    }

}
