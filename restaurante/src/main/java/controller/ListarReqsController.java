package controller;

import dao.Requisicoes;
import model.Requisicao;
import view.ListarReqsView;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class ListarReqsController {

    private ListarReqsView view;
    private Requisicoes requisicoes;

    public ListarReqsController() {
        this.requisicoes = Requisicoes.getInstancia();
        this.view = new ListarReqsView();
        this.view.setVisible(true);
        this.carregaTabelaRequisicoes();

        

        this.view.getListarPedidosBtn().addActionListener((e) -> {
            instanciaListarPedidoControllerComAReqSelecionada();
        });

    }

    public void carregaTabelaRequisicoes() {
        Object colunas[] = {"ID", "Nome do Cliente", "Qtd. Pessoas", "Status", "Numero da Mesa"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = requisicoes.listar().stream()
                .filter(r -> r.getStatus())
                .map(r -> new Object[]{
            r.getIdRequisicao(),
            r.getCliente().getNome(),
            r.getCliente().getQtdPessoas(),
            r.getStatus(),
            r.getMesa() != null ? r.getMesa().getIdMesa() : "Sem mesa"})
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbReqs().setModel(tm);
    }

    

    private void instanciaSelecionarMesaControllerComAReqSelecionada() {
        int selectedRow = view.getTbReqs().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbReqs().getValueAt(selectedRow, 0);
            Requisicao requisicao = requisicoes.obter(idRequisicao);

            if (requisicao != null) {
                new SelecionarMesaController(requisicao, this);
            } else {
                JOptionPane.showMessageDialog(view, "Requisição não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma requisição para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    

    private void instanciaListarPedidoControllerComAReqSelecionada() {
        int selectedRow = view.getTbReqs().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbReqs().getValueAt(selectedRow, 0);
            Requisicao requisicao = requisicoes.obter(idRequisicao);

            if (requisicao != null) {
                new ListarPedidoController(requisicao);
            } else {
                JOptionPane.showMessageDialog(view, "Requisição não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma requisição para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

}
