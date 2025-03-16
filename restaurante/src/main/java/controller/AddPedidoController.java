package controller;

import dao.*;
import factory.ItemProdutoFactory;
import model.ItemProduto;
import model.Pedido;
import model.Produto;
import model.Requisicao;
import view.AddPedidoView;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class AddPedidoController {

    private Requisicoes requisicoes;
    private Pedidos pedidos;
    private AddPedidoView view;
    private Cardapio cardapio;
    private Requisicao requisicaoSelecionada;
    private ListarPedidoController listarPedidoController;
    private Pedido pedido;
    private ItemProdutoFactory itemProdutoFactory; // Fábrica injetada

    // Injete a fábrica via construtor
    public AddPedidoController(Requisicao requisicaoSelecionada, ListarPedidoController listarPedidoController,
            ItemProdutoFactory itemProdutoFactory) {
        this.cardapio = Cardapio.getInstancia();
        this.requisicaoSelecionada = requisicaoSelecionada;
        this.view = new AddPedidoView();
        this.pedido = requisicaoSelecionada.getPedido();
        this.pedidos = Pedidos.getInstancia();
        this.itemProdutoFactory = itemProdutoFactory;
        this.view.getCancelarBtn().addActionListener((e) -> cancelar());
        this.view.getAddPedidoBtn().addActionListener((e) -> selecionarProduto());
        this.requisicoes = Requisicoes.getInstancia();
        this.carregaTabelaCardapio();

        this.listarPedidoController = listarPedidoController;
        this.view.setTitle("Selecionar Produto");
        this.view.setVisible(true);
    }

    private void cancelar() {
        this.view.dispose();
    }

    private void carregaTabelaCardapio() {
        Object colunas[] = { "ID", "Nome", "Preço" };
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = cardapio.listar().stream()
                .map(p -> new Object[] { p.getIdProduto(), p.getNome(), p.getPreco() })
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);
        view.getTbCardapio().setModel(tm);
    }

    private void clearFields() {
        this.view.getQntText().setText("");
    }

    private void selecionarProduto() {
        int linhaSelecionada = view.getTbCardapio().getSelectedRow();
        if (linhaSelecionada >= 0) {
            String qntText = view.getQntText().getText();

            // Validação da quantidade
            if (qntText.isEmpty() || !qntText.matches("\\d+")) {
                JOptionPane.showMessageDialog(view, "Por favor, insira uma quantidade válida.");
                return;
            }

            int quantidade = Integer.parseInt(qntText);
            if (quantidade <= 0) {
                JOptionPane.showMessageDialog(view, "A quantidade deve ser maior que zero.");
                return;
            }

            // Verificar se a requisição possui uma mesa associada
            if (requisicaoSelecionada.getMesa() == null) {
                JOptionPane.showMessageDialog(view,
                        "A requisição deve ter uma mesa associada antes de adicionar itens ao pedido.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obter o ID do produto selecionado
            int idProduto = (int) view.getTbCardapio().getValueAt(linhaSelecionada, 0);

            // Obter o produto selecionado pelo ID
            Produto produtoSelecionado = cardapio.obter(idProduto);
            if (produtoSelecionado == null) {
                JOptionPane.showMessageDialog(view, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Pedido pedido = requisicaoSelecionada.getPedido();
            // Cria o ItemProduto usando a fábrica
            ItemProduto item = itemProdutoFactory.criarItemProduto(produtoSelecionado, quantidade);
            pedido.adicionarItem(item);
            requisicaoSelecionada.setPedido(pedido);
            pedido.ativar();
            JOptionPane.showMessageDialog(view, "Produto adicionado com sucesso!");
            clearFields();

            // Atualiza a tabela de pedidos na ListarPedidoController
            listarPedidoController.carregaTabelaPedido();
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um produto para continuar.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }

        requisicoes.grava();
        pedidos.grava();
    }
}
