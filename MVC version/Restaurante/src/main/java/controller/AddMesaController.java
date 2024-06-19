/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Mesas;
import model.Mesa;
import view.*;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class AddMesaController {

    private AddMesaView view;
    private Mesas mesas;

    public AddMesaController() {
        this.mesas = Mesas.getInstancia();
        this.view = new AddMesaView();
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
                JOptionPane.showMessageDialog(view, "A capacidade não pode ser menor que 0.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                mesas.adicionar(new Mesa(capacidade));
                JOptionPane.showMessageDialog(view, "Mesa adicionada com sucesso!");
                this.view.dispose(); // Fecha a janela após adicionar a mesa
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void AddBtn() {

    }

}
