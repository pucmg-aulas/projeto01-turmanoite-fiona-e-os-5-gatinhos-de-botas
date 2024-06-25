package controller;

import dao.Requisicoes;
import model.Requisicao;
import view.ListarFilaView;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável por controlar a interface de listagem da fila de requisições.
 */
public class ListarFilaController {

    private ListarFilaView view;
    private Requisicoes requisicoes;

    public ListarFilaController() {
        this.requisicoes = requisicoes.getInstancia();
        this.view = new ListarFilaView();
        this.view.setVisible(true);
        this.carregaTabelaFila();

        this.view.getExcluirBtn().addActionListener((e) -> {
            this.excluirRequisicao();
        });

        this.view.getSelecionarMesaBtn().addActionListener((e) -> {
            instanciaSelecionarMesaControllerComAReqSelecionada();
        });
        
        this.view.getAdicionarReqBtn().addActionListener((e) -> {
            new AddReqController(this);

        });
    }

    public void carregaTabelaFila() {
        Object colunas[] = {"ID", "Nome do Cliente", "Qtd. Pessoas", "Status", "Numero da Mesa"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        // Filtrar apenas requisições não ativas (status = false)
        List<Object[]> linhas = requisicoes.listar().stream()
                .filter(r -> !r.getStatus()) // Adicionar a condição de status
                .map(r -> new Object[]{
                        r.getIdRequisicao(),
                        r.getCliente().getNome(),
                        r.getCliente().getQtdPessoas(),
                        r.getStatus() ? "Ativo" : "Inativo", // Exibe "Ativo" ou "Inativo" na tabela
                        r.getMesa() != null ? r.getMesa().getIdMesa() : "Sem mesa"
                })
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbFila().setModel(tm);
    }

    public void excluirRequisicao() {
        int selectedRow = view.getTbFila().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbFila().getValueAt(selectedRow, 0);
            Requisicao requisicao = requisicoes.obter(idRequisicao);

            if (requisicao != null) {
                int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir a requisição?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    requisicoes.removerReq(requisicao);
                    carregaTabelaFila();
                    JOptionPane.showMessageDialog(view, "Requisição excluída com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Requisição não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma requisição para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void instanciaSelecionarMesaControllerComAReqSelecionada() {
        int selectedRow = view.getTbFila().getSelectedRow();
        if (selectedRow >= 0) {
            int idRequisicao = (int) view.getTbFila().getValueAt(selectedRow, 0);
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
}
