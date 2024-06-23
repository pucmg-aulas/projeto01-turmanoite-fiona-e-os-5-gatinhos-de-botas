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
        aplicarDescontoEPrazo(0, 0.0);
    }

    private void pagarPix() {
        aplicarDescontoEPrazo(0, 0.0145, 10);
    }

    private void pagarDebito() {
        aplicarDescontoEPrazo(14, 0.014);
    }

    private void pagarCredito() {
        aplicarDescontoEPrazo(30, 0.031);
    }

    private void aplicarDescontoEPrazo(int prazo, double descontoPercentual) {
        aplicarDescontoEPrazo(prazo, descontoPercentual, Double.MAX_VALUE);
    }

    private void aplicarDescontoEPrazo(int prazo, double descontoPercentual, double limiteDesconto) {
        Pedido pedido = requisicao.getPedido();

        if (pedido == null) {
            JOptionPane.showMessageDialog(view, "Não há pedido associado.");
            return;
        }

        double valorTotal = pedido.getTotalProdutos();
        double desconto = Math.min(valorTotal * descontoPercentual, limiteDesconto);
        double valorComDesconto = valorTotal - desconto;

        pedido.setPrazo(prazo);
        pedido.setDesconto(desconto);
        pedido.setValorFinal(valorComDesconto);

        JOptionPane.showMessageDialog(view, "Pagamento realizado. Valor final: R$ " + valorComDesconto);
    }
}
