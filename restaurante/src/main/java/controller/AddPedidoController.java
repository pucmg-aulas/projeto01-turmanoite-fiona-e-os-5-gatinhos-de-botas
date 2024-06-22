package controller;

import dao.Cardapio;
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

    private AddPedidoView view;
    private Cardapio cardapio;
    private Requisicao requisicaoSelecionada;
    private ListarReqsController listarReqsController;

    public AddPedidoController(Requisicao requisicaoSelecionada, ListarReqsController listarReqsController) {
        this.cardapio = Cardapio.getInstancia();
        this.requisicaoSelecionada = requisicaoSelecionada;
        this.view = new AddPedidoView();
        this.listarReqsController = listarReqsController;

        this.view.getCancelarBtn().addActionListener((e) -> cancelar());
        this.view.getAddPedidoBtn().addActionListener((e) -> selecionarProduto());

        this.carregaTabelaCardapio();

        this.view.setTitle("Selecionar Produto");
        this.view.setVisible(true);
    }

    private void cancelar() {
        this.view.dispose();
    }

    private void carregaTabelaCardapio() {
        Object colunas[] = {"ID", "Nome", "Preço"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = cardapio.listar().stream()
                .map(p -> new Object[]{p.getIdProduto(), p.getNome(), p.getPreco()})
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

            // Obter o ID do produto selecionado
            int idProduto = (int) view.getTbCardapio().getValueAt(linhaSelecionada, 0);

            // Obter o produto selecionado pelo ID
            Produto produtoSelecionado = cardapio.obter(idProduto);
            if (produtoSelecionado == null) {
                JOptionPane.showMessageDialog(view, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ItemProduto itemProduto = new ItemProduto(produtoSelecionado, quantidade);

            Pedido pedido = requisicaoSelecionada.getPedido();

            if (pedido == null) {
                // Cria um novo pedido se ainda não existir na requisição
                pedido = new Pedido();
                requisicaoSelecionada.setPedido(pedido);
            }

            // Adiciona o itemProduto ao pedido
            pedido.adicionarItem(itemProduto);

            // Exibe mensagem de sucesso e limpa os campos
            JOptionPane.showMessageDialog(view, "Produto adicionado com sucesso!");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(view, "Selecione um produto para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // Atualiza a tabela de requisições na ListarReqsController
        listarReqsController.carregaTabelaRequisicoes();
    }
}
