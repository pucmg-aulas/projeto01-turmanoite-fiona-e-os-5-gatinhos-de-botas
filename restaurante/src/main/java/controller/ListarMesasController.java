/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exception.MesaNaoAlocadaException;
import dao.*;
import model.*;
import view.ListarMesasView;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class ListarMesasController {

    private Requisicoes requisicoes;
    private ListarMesasView view;
    private Mesas mesas;

    public ListarMesasController() {

        this.requisicoes = Requisicoes.getInstancia();
        this.mesas = Mesas.getInstancia();
        this.view = new ListarMesasView();
        this.view.setVisible(true);
        this.carregaTabelaMesas();

        this.view.getAddMesaViewBtn().addActionListener((e) -> {
            new AddMesaController(this);
        });

        this.view.getAtualizaTableBtn().addActionListener((e) -> {
            this.carregaTabelaMesas();
        });

        this.view.getExcluirMesaBtn().addActionListener((e) -> {
            this.excluirMesa();
        });

        this.view.getDesocuparBtn().addActionListener((e) -> {
            this.desocuparMesa();
        });

    }

    public void carregaTabelaMesas() {

        Object colunas[] = {"ID", "Capacidade", "Disponível"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = mesas.listar().stream()
                .map(m -> new Object[]{m.getIdMesa(), m.getCapacidade(), m.getStatus()})
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbMesas().setModel(tm);
    }

    public void excluirMesa() {
        int selectedRow = view.getTbMesas().getSelectedRow();
        if (selectedRow >= 0) {
            int idMesa = (int) view.getTbMesas().getValueAt(selectedRow, 0);

            Mesa mesa = mesas.obter(idMesa);

            try {
                Requisicao r = mesa.reqDaMesa();

                JOptionPane.showMessageDialog(view, "Não é possível excluir uma mesa que possui uma requisição ativa.", "Erro", JOptionPane.ERROR_MESSAGE);

            } catch (MesaNaoAlocadaException e) {
                int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir a mesa?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    mesas.removerMesa(mesa);
                    carregaTabelaMesas();
                    JOptionPane.showMessageDialog(view, "Mesa excluída com sucesso.");
                }
            }

        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma mesa para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        requisicoes.grava();
        mesas.grava();
    }

    public void desocuparMesa() {
        int selectedRow = view.getTbMesas().getSelectedRow();
        if (selectedRow >= 0) {
            int idMesa = (int) view.getTbMesas().getValueAt(selectedRow, 0);
            Mesa mesa = mesas.obter(idMesa);

            try {
                Requisicao r = mesa.reqDaMesa();

                if (r.getPedido().getAtivo() != 1) {
                    int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja desocupar a mesa?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        mesa.setStatus(true);
                        r.setStatus(false);
                        carregaTabelaMesas();
                        JOptionPane.showMessageDialog(view, "Mesa desocupada com sucesso.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "A mesa possui uma requisição com pedido não finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (MesaNaoAlocadaException e) {
                JOptionPane.showMessageDialog(view, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma mesa para desocupar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // Salva as mudanças nos arquivos
        requisicoes.grava();
        mesas.grava();
    }

}
