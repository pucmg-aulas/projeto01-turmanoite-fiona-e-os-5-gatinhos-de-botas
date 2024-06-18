/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FilaDeEspera;
import dao.Requisicoes;
import dao.Mesas;

import model.*;
import view.AddReqView;
import javax.swing.JOptionPane;

public class AddReqController {

    private Requisicoes requisicoes;
    private AddReqView view;
    private Mesas mesas;
    
    
    public AddReqController() {
        this.requisicoes = Requisicoes.getInstancia();
        this.view = new AddReqView();
        
        
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

    private void addReq() {
        String nome = this.view.getjTextNome().getText();
        if(Integer.parseInt(this.view.getjTextQnt().getText()) > 10 || Integer.parseInt(this.view.getjTextQnt().getText()) < 0 ){
            JOptionPane.showMessageDialog(view, "A capacidade máxima das nossas mesas é 10 convidados. Por favor, insira um número"
                    + " válido");
        }
        else{
            int qtdPessoas = Integer.parseInt(this.view.getjTextQnt().getText());
            Cliente c = new Cliente(nome, qtdPessoas);
            Requisicao r = new Requisicao(c);
            Requisicoes.getInstancia().adicionar(r);
            JOptionPane.showMessageDialog(view, "Requisição no nome de " + nome + ", para " + qtdPessoas + " pessoa(s)");
            
            r.setMesa(EncontrarMesa(r));
            JOptionPane.showMessageDialog(view, "Você foi enviado para a mesa " + r.getMesa().getIdMesa() );
        }        
    }
    
    private void ColocarNaFilaDeEspera(Requisicao r){
        FilaDeEspera.getInstancia().adicionar(r);
        int lugarDaFila = FilaDeEspera.getInstancia().listar().size();
        JOptionPane.showMessageDialog(view, "Você esta na fila de espera. Sua posição na fila é " + lugarDaFila);
    }
    
    
    private Mesa EncontrarMesa(Requisicao req) {
        this.mesas = Mesas.getInstancia();
        int convidados = req.getCliente().getQtdPessoas();

        for (Mesa mesa : mesas.getMesas()) {
            if (convidados <= mesa.getCapacidade() && !mesa.getStatus()) {
                mesa.ocuparMesa();
                return mesa;
            }
        }

        // Se nenhuma mesa foi encontrada, coloca na fila de espera
        ColocarNaFilaDeEspera(req);
        return null;
    }


}
