import java.util.ArrayList;

public class Pedido {
    private ArrayList<ItemProduto> produtos = new ArrayList<ItemProduto>();
    private static double TAXA = 1.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;
    private int ativo = 1;

    public Pedido() {
        this.idPedido = ++contadorPedidos;

    }

    public ArrayList<ItemProduto> getItens() {
        return produtos;
    }

    public void addItem(ItemProduto p) {
        produtos.add(p);

    }

    public void imprimePedido() {
        System.out.println("---------");
        System.out.println("Pratos e produtos:");
        for (ItemProduto item : produtos) {

            System.out.println(
                    "-" + item.getProduto().getNome() + " - R$" + item.getProduto().getPreço() +
                            " (" + item.getProduto().getIdProduto() + ")");
        }
        System.out.println("---------");
    }

    public double calculaTotal() {
        totalProdutos = 0;
        for (ItemProduto itemProduto : produtos) {
            totalProdutos += itemProduto.getValorTotal();
        }
        return totalProdutos * TAXA;

    }

    public double calculaDividido(Requisicao req) {
        return calculaTotal() / req.getConvidados();
    }

    public void ativaPedido() {
        this.ativo = 2;
    }

    public void finaliza() {
        this.ativo = 3;
    }

    public int getStatus() {
        return this.ativo;
    }

}
