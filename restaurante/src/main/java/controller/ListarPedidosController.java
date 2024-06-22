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
import view.ListarPedidosView;

/**
 *
 * @author ticok
 */
public class ListarPedidosController {
    
    private ListarPedidosView view;
    private Pedidos pedidos;
    private Requisicoes requisicoes;
    private ListarReqsController listarReqController;

    public ListarPedidosController() {
            this.pedidos = Pedidos.getInstancia();
        this.view = new ListarPedidosView();
        this.view.setVisible(true);
        this.carregaTabelaPedidos();

        this.view.getAdcionarBtn().addActionListener((e) -> {
            instanciaAddPedidoControllerComAReqSelecionada();
        });

        this.view.getFinalizarBtn().addActionListener((e) -> {
            this.finalizarPedido();
        });
    }
    
        public void carregaTabelaPedidos() {
        Object colunas[] = {"ID", "Nome do Cliente", "Qtd. Pessoas", "Status", "Numero da Mesa"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = requisicoes.listar().stream()
                .map(r -> new Object[]{
                        r.getIdRequisicao(),
                        r.getCliente().getNome(),
                        r.getCliente().getQtdPessoas(),
                        r.getStatus(),
                        r.getMesa() != null ? r.getMesa().getIdMesa() : "Sem mesa"})
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbPedidos().setModel(tm);
    }
    
    private void instanciaAddPedidoControllerComAReqSelecionada() {
        int selectedRow = view.getTbPedidos().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbPedidos().getValueAt(selectedRow, 0);
            Requisicao requisicao = requisicoes.obter(idRequisicao);

            if (requisicao != null) {
                new AddPedidoController(requisicao, this.listarReqController );
            } else {
                JOptionPane.showMessageDialog(view, "Requisição não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma requisição para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

      public void finalizarPedido() {
        int selectedRow = view.getTbPedidos().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbPedidos().getValueAt(selectedRow, 0);
            Requisicao requisicao = requisicoes.obter(idRequisicao);

            if (requisicao != null) {
                int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir a requisição?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    requisicoes.removerReq(requisicao);
                    carregaTabelaPedidos();
                    JOptionPane.showMessageDialog(view, "Requisição excluída com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Requisição não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma requisição para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }
  
}
