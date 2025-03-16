package factory;

import model.ItemProduto;
import model.Produto;

public class ItemProdutoFactoryImpl implements ItemProdutoFactory {
    @Override
    public ItemProduto criarItemProduto(Produto produto, int quantidade) {
        // Aqui você pode inserir validações ou lógica adicional
        return new ItemProduto(produto, quantidade);
    }
}
