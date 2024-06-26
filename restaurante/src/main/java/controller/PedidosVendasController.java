package controller;

import dao.Pedidos;
import model.Pedido;
import view.PedidosVendasView;
import java.time.LocalDate;
import java.util.Map;
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
        this.carregaTabelaVendas();
    }

    public void carregaTabelaPedido() {
        Object colunas[] = {"Nmr Requisição", "Total", "Forma Pgto", "Data"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        List<Pedido> pedidosFinalizados = pedidos.listar().stream()
                .filter(pedido -> pedido.getAtivo() == 2)
                .collect(Collectors.toList());

        for (Pedido pedido : pedidosFinalizados) {
            Object[] linha = new Object[]{
                pedido.getIdPedido(),
                pedido.getPagamento().getValorFinal(),
                pedido.getPagamento().getTipo(),
                pedido.getPagamento().getDataPagamento()
            };
            tm.addRow(linha);
        }

        view.getTbPedidos().setModel(tm);
    }

    public void carregaTabelaVendas() {
        Object colunas[] = {"Data Recebimento", "Total $"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        // Filtrar pedidos finalizados
        List<Pedido> pedidosFinalizados = pedidos.listar().stream()
                .filter(pedido -> pedido.getAtivo() == 2)
                .collect(Collectors.toList());

        // Agrupar por data de recebimento (data de pagamento + prazo de recebimento) e somar os valores finais
        Map<LocalDate, Double> vendasPorData = pedidosFinalizados.stream()
                .collect(Collectors.groupingBy(
                        pedido -> pedido.getPagamento().getDataPagamento()
                                .plusDays(pedido.getPagamento().getPrazoRecebimento()),
                        Collectors.summingDouble(pedido -> pedido.getPagamento().getValorFinal())
                ));

        // Preencher a tabela com as vendas agrupadas por data de recebimento
        vendasPorData.forEach((dataRecebimento, total) -> {
            Object[] linha = new Object[]{dataRecebimento, total};
            tm.addRow(linha);
        });

        view.getTbVendas().setModel(tm);
    }
}
