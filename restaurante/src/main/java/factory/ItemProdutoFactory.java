package factory;

import model.ItemProduto;
import model.Produto;

public interface ItemProdutoFactory {
    ItemProduto criarItemProduto(Produto produto, int quantidade);
}
