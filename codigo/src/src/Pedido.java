import java.util.ArrayList;

public class Pedido {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private static double TAXA = 0.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;

    public Pedido() {
        this.idPedido = ++contadorPedidos;

    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto p) {
        produtos.add(p);

    }

    public void imprimePedido() {
        System.out.println("---------");
        System.out.println("Pratos e produtos:");
        for (Produto produto : produtos) {
            System.out.println(
                    produtos.indexOf(produto) + 1 + "-" + produto.getNome() + " - R$" + produto.getPreço() + " ("
                            + produto.getIdProduto() + ")");
        }
        System.out.println("---------");
    }

    public void calculaTotal() {

    }

}
