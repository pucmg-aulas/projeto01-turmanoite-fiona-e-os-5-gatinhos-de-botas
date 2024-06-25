package controller;

import dao.Pedidos;
import model.Pedido;
import view.PedidosVendasView;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

public class PedidosVendasController {

    private Pedidos pedidos;
    private PedidosVendasView view;

    public PedidosVendasController() {
        this.pedidos = Pedidos.getInstancia();
        this.view = new PedidosVendasView();
        this.view.setVisible(true);
        this.carregaTabelaPedido();
    }

    public void carregaTabelaPedido() {
        Object colunas[] = {"Nmr Requisição", "Total", "Forma Pgto", "Data"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Pedido> pedidosFinalizados = pedidos.listar().stream()
                .filter(pedido -> pedido.getAtivo() == 2)
                .collect(Collectors.toList());

        for (Pedido pedido : pedidosFinalizados) {
            Object[] linha = new Object[]{
                    pedido.reqDoPedido().getIdRequisicao(),
                    pedido.getTotalComTaxa(), // Alterei para getTotalComTaxa para incluir a taxa
                    pedido.getPagamento().getTipo(),
                    pedido.getPagamento().getDataPagamento()
            };
            tm.addRow(linha);
        }

        view.getTbPedidos().setModel(tm);
    }
}
