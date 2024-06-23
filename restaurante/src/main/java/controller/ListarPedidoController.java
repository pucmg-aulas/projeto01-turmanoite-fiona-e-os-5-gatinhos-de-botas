/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Pedidos;
import dao.Requisicoes;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Requisicao;
import view.ListarPedidoView;

/**
 *
 * @author ticok
 */
public class ListarPedidoController {

    private Requisicao requisicao;
    private ListarPedidoView view;
    private ListarReqsController listarReqController;

    public ListarPedidoController(Requisicao r, ListarReqsController listarReqController) {
        this.requisicao = r;
        this.view = new ListarPedidoView();
        this.view.setVisible(true);
        this.carregaTabelaPedido();
        this.listarReqController = listarReqController;

        this.view.getAdicionarBtn().addActionListener((e) -> {
            new AddPedidoController(r, listarReqController);
        });

        this.view.getFinalizarBtn().addActionListener((e) -> {
            this.finalizarPedido();
        });
    }

    public void carregaTabelaPedido() {
        Object colunas[] = {"quantidade", "Nome", "Preço unit.", "Preço total"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = requisicao.getPedido().getProdutos().stream()
                .map(i -> new Object[]{
            i.getQnt(),
            i.getProduto().getNome(),
            i.getProduto().getPreco(),
            i.getValorTotal()})
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbPedidos().setModel(tm);
    }

    public void finalizarPedido() {

        int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja finalizar a requisição?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            requisicao.getPedido().desativar();
            carregaTabelaPedido();
            JOptionPane.showMessageDialog(view, "Pedido finalizado com sucesso.");
        }

    }

}
