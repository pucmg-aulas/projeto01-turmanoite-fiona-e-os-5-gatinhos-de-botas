package controller;

import dao.Requisicoes;
import dao.Mesas;
import model.Cliente;
import model.Requisicao;
import view.AddReqView;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.stream.Collectors;

public class AddReqController {

    private Requisicoes requisicoesModel;
    private AddReqView view;
    private ListarReqsController listarReqsController;
    private Mesas mesasModel;

    public AddReqController(ListarReqsController listarReqsController) {
        this.requisicoesModel = Requisicoes.getInstancia();
        this.mesasModel = Mesas.getInstancia();
        this.view = new AddReqView();
        this.listarReqsController = listarReqsController;

        this.view.getjButtonAddReq().addActionListener((e) -> {
            addReq();
        });

        this.view.getjButtonCancela().addActionListener((e) -> {
            cancelar();
        });

        this.view.setTitle("Cadastrar Requisição");

        this.view.setVisible(true);
    }

    public void showView() {
        view.setVisible(true);
    }

    private void cancelar() {
        this.view.dispose();
    }

    private void clearFields() {
        this.view.getjTextNome().setText("");
        this.view.getjTextQnt().setText("");
    }

    private void addReq() {
        String nome = this.view.getjTextNome().getText();
        int qtdPessoas;

        try {
            qtdPessoas = Integer.parseInt(this.view.getjTextQnt().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um número válido para a quantidade de pessoas.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (qtdPessoas <= 0) {
            JOptionPane.showMessageDialog(view, "A quantidade de pessoas deve ser maior que zero.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente c = new Cliente(nome, qtdPessoas);
        Requisicao r = new Requisicao(c);

        // Verificar se há mesas com capacidade suficiente
        boolean mesaDisponivel = mesasModel.listar().stream()
                .anyMatch(m -> m.getCapacidade() >= qtdPessoas && m.getStatus() == true);

        if (!mesaDisponivel) {
            JOptionPane.showMessageDialog(view, "Não existem mesas com capacidade suficiente para " + qtdPessoas + " pessoas no momento.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        requisicoesModel.adicionar(r);
        JOptionPane.showMessageDialog(view, "Requisição no nome de " + nome + ", para " + qtdPessoas + " pessoas, adicionada com sucesso.");

        clearFields();
        listarReqsController.carregaTabelaRequisicoes();
    }
}
