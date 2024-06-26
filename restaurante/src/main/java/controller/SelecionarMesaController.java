/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.*;
import model.*;
import view.SelecionarMesaView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class SelecionarMesaController {

    private SelecionarMesaView view;
    private Mesas mesas;
    private Requisicoes requisicoes;
    private Requisicao requisicaoSelecionada;
    private ListarReqsController listarReqsController;
    private ListarFilaController listarFilaController;

    public SelecionarMesaController(Requisicao requisicaoSelecionada, ListarReqsController listarReqsController) {
        this.mesas = Mesas.getInstancia();
        this.requisicoes = Requisicoes.getInstancia();
        this.requisicaoSelecionada = requisicaoSelecionada;
        this.view = new SelecionarMesaView();
        this.listarReqsController = listarReqsController;

        this.view.getCancelarBtn().addActionListener((e) -> {
            cancelar();
        });

        this.view.getSelecionarMesaBtn().addActionListener((e) -> {
            selecionarMesa();
        });

        this.carregaTabelaMesas();

        this.view.setTitle("Selecionar Mesa");
        this.view.setVisible(true);
    }

    public SelecionarMesaController(Requisicao requisicaoSelecionada, ListarFilaController listarFilaController) {
        this.mesas = Mesas.getInstancia();
        this.requisicoes = Requisicoes.getInstancia();
        this.requisicaoSelecionada = requisicaoSelecionada;
        this.view = new SelecionarMesaView();
        this.listarFilaController = listarFilaController;

        this.view.getCancelarBtn().addActionListener((e) -> {
            cancelar();
        });

        this.view.getSelecionarMesaBtn().addActionListener((e) -> {
            selecionarMesa();
        });

        this.carregaTabelaMesas();

        this.view.setTitle("Selecionar Mesa");
        this.view.setVisible(true);
    }

    private void cancelar() {
        this.view.dispose();
    }

    private void carregaTabelaMesas() {
        Object colunas[] = {"ID", "Capacidade", "Disponível"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Object[]> linhas = mesas.listar().stream()
                .map(m -> new Object[]{m.getIdMesa(), m.getCapacidade(), m.getStatus()})
                .collect(Collectors.toList());

        linhas.forEach(tm::addRow);

        view.getTbSelecinarMesas().setModel(tm);
    }

    private void selecionarMesa() {
        if (requisicaoSelecionada.getMesa() != null) {
            JOptionPane.showMessageDialog(view, "A requisição já possui uma mesa associada. Não é possível trocar de mesa.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int selectedRow = view.getTbSelecinarMesas().getSelectedRow();
        if (selectedRow >= 0) {
            int idMesa = (int) view.getTbSelecinarMesas().getValueAt(selectedRow, 0);
            Mesa mesa = mesas.obter(idMesa);

            if (mesa != null && mesa.getStatus() && requisicaoSelecionada.getCliente().getQtdPessoas() <= mesa.getCapacidade()) {
                requisicaoSelecionada.setMesa(mesa);
                requisicaoSelecionada.setStatus(true);
                mesa.setStatus(false);  // Definir mesa como ocupada
                JOptionPane.showMessageDialog(view, "Mesa selecionada com sucesso.");
                this.view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "A mesa selecionada não está disponível ou não comporta a quantidade de pessoas.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecione uma mesa para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        if (this.listarFilaController != null) {
            this.listarFilaController.carregaTabelaFila();
        }
        if (this.listarReqsController != null) {
            this.listarReqsController.carregaTabelaRequisicoes();
        }
        requisicoes.grava();
        mesas.grava();

    }
}
