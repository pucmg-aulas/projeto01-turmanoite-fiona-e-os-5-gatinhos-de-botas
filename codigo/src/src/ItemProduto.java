public class ItemProduto {

    private int qnt;
    private Produto produto;
    private double valorTotal;

    public ItemProduto(Produto p, int quantidade) {
        this.qnt = quantidade;
        this.produto = p;
        this.valorTotal = produto.getPreço() * quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getQnt() {
        return qnt;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

}