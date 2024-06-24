package controller;

import dao.Pedidos;
import model.Pedido;
import model.Requisicao;
import view.RealizarPagamentoView;
import javax.swing.JOptionPane;

public class RealizarPagamentoController {
    
    private RealizarPagamentoView view;
    private Pedidos pedidos;
    private Requisicao requisicao;
    
    public RealizarPagamentoController(Requisicao req) {
        this.requisicao = req;
        this.view = new RealizarPagamentoView();
        this.pedidos = Pedidos.getInstancia();
        
        this.view.getPixBtn().addActionListener((e) -> {
            pagarPix();
        });
        
        this.view.getDinheiroBtn().addActionListener((e) -> {
            pagarDinheiro();
        });
        
        this.view.getDebitoBtn().addActionListener((e) -> {
            pagarDebito();
        });
        
        this.view.getCreditoBtn().addActionListener((e) -> {
            pagarCredito();
        });
        
        
        this.view.setVisible(true);
    }

    private void pagarDinheiro() {
    }

    private void pagarPix() {
    }

    private void pagarDebito() {
    }

    private void pagarCredito() {
    }

    
}
