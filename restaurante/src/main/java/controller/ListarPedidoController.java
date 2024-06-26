package controller;

import dao.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Requisicao;
import view.ListarPedidoView;

public class ListarPedidoController {
private Requisicoes requisicoes;
    private Requisicao requisicao;
    private ListarPedidoView view;
    private Pedidos p;

    public ListarPedidoController(Requisicao r) {
        this.requisicao = r;
        this.p = Pedidos.getInstancia();
        this.requisicoes = Requisicoes.getInstancia();
        this.view = new ListarPedidoView();
        this.view.setVisible(true);
        this.carregaTabelaPedido();
        

        // Adicionar verificação antes de instanciar o AddPedidoController
        this.view.getAdicionarBtn().addActionListener((e) -> {
            if (requisicao.getPedido().getAtivo() != 2) { // Verificar se o pedido está ativo
                new AddPedidoController(r, this);
            } else {
                JOptionPane.showMessageDialog(view, "Não é possível adicionar itens a um pedido finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Verificação para finalizar o pedido
        this.view.getFinalizarBtn().addActionListener((e) -> {
            if (requisicao.getPedido().getAtivo() == 1) { // Verificar se o pedido está ativo
                new RealizarPagamentoController(r, this);
            } else {
                JOptionPane.showMessageDialog(view, "Esse pedido não possui itens ou já foi finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void carregaTabelaPedido() {
        Object colunas[] = {"Quantidade", "Nome", "Preço Unit.", "Preço Total", "Estado"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = requisicao.getPedido().getProdutos().stream()
                .map(i -> new Object[]{
            i.getQnt(),
            i.getProduto().getNome(),
            i.getProduto().getPreco(),
            i.getValorTotal(),
            requisicao.getPedido().getEstado()
        })
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbPedidos().setModel(tm);
    }

    public void finalizarPedido() {
        int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja finalizar a requisição?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            requisicao.getPedido().desativar();
            p.adicionar(requisicao.getPedido());
            carregaTabelaPedido();
            
            requisicoes.grava();
            JOptionPane.showMessageDialog(view, "Pedido finalizado com sucesso.");
        }

        // Salvar as requisições no banco de dados
        Requisicoes r = Requisicoes.getInstancia();
        p.grava();
        r.grava();
        p.imprimirPedidos();
    }
}
