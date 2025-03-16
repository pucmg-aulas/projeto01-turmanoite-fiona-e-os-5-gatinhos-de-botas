package controller;

import dao.Mesas;
import factory.MesaFactory;
import model.Mesa;
import view.AddMesaView;
import javax.swing.JOptionPane;

public class AddMesaController {

    private AddMesaView view;
    private Mesas mesas;
    private ListarMesasController listarMesasController;
    private MesaFactory mesaFactory;

    // Injete a fábrica via construtor (isso facilita testes e desacoplamento)
    public AddMesaController(ListarMesasController listarMesasController, MesaFactory mesaFactory) {
        this.mesas = Mesas.getInstancia();
        this.view = new AddMesaView();
        this.listarMesasController = listarMesasController;
        this.mesaFactory = mesaFactory;

        this.view.getAddBtn().addActionListener((e) -> {
            this.adicionarMesa();
        });

        this.view.getCancelarBtn().addActionListener((e) -> {
            cancelar();
        });

        this.view.setTitle("Cadastrar Mesa");
        this.view.setVisible(true);
    }

    private void cancelar() {
        this.view.dispose();
    }

    private void adicionarMesa() {
        try {
            int capacidade = Integer.parseInt(this.view.getQntText().getText());
            if (capacidade <= 0) {
                JOptionPane.showMessageDialog(view, "A capacidade não pode ser menor ou igual a 0.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Cria a mesa usando a fábrica
                Mesa novaMesa = this.mesaFactory.criarMesa(capacidade);
                mesas.adicionar(novaMesa);
                JOptionPane.showMessageDialog(view, "Mesa adicionada com sucesso!");
                this.view.dispose(); // Fecha a janela após adicionar a mesa
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um número válido.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.listarMesasController.carregaTabelaMesas();
    }
}
