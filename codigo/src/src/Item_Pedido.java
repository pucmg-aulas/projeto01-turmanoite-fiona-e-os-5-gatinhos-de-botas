public class Item_Pedido {
    public Produto produto;
    public int quantidade;

    public Item_Pedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Item_Pedido getItem_Pedido() {
        return this;
    }	

    public Produto getProduto() {
        return produto;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
}
