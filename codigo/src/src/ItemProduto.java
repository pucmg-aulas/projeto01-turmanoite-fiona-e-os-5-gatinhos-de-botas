public class ItemProduto {

    private int qnt;
    private Produto produto;

    public ItemProduto(Produto p, int quantidade) {
        this.qnt = quantidade;
        this.produto = p;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public int getQnt() {
        return qnt;
    }

}