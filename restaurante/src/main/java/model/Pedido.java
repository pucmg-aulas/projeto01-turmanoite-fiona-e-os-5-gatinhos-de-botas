package model;

import dao.Requisicoes;
import interfaces.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Serializable {

    private static final double TAXA = 1.1; // Taxa de 10%
    private static int contadorPedidos = 0;

    private int idPedido;
    private List<ItemProduto> produtos;
    private double totalProdutos;
    private int ativo;
    private FormaDePagamento pagamento;

    public Pedido() {
        this.idPedido = ++contadorPedidos;
        this.produtos = new ArrayList<>();
        this.totalProdutos = 0.0;
        this.ativo = 0;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getTotalProdutos() {
        return totalProdutos;
    }

    public int getAtivo() {
        return ativo;
    }

    public List<ItemProduto> getProdutos() {
        return produtos;
    }

    public void adicionarItem(ItemProduto item) {
        this.produtos.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItemProduto item) {
        if (this.produtos.remove(item)) {
            calcularValorTotal();
        }
    }

    public void calcularValorTotal() {
        this.totalProdutos = this.produtos.stream()
                .mapToDouble(ItemProduto::getValorTotal)
                .sum();
    }

    public double getTotalComTaxa() {
        return this.totalProdutos * TAXA;
    }

    public void ativar() {
        this.ativo = 1;
    }

    public void desativar() {
        this.ativo = 2;
    }

    public void setFormaDePagamento(FormaDePagamento p) {
        this.pagamento = p;
    }

    public FormaDePagamento getPagamento() {
        return pagamento;
    }

    public String getEstado() {
        switch (this.ativo) {
            case 0:
                return "não iniciado"; // Pedido não foi iniciado ainda
            case 1:
                return "em aberto"; // Pedido está em andamento
            case 2:
                return "finalizado"; // Pedido foi finalizado
            default:
                return "estado desconhecido"; // Valor de 'ativo' não é esperado
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - Total: R$ " + totalProdutos + " (Ativo: " + (ativo == 1 ? "Sim" : "Não") + ")";
    }

    public Requisicao reqDoPedido() {
        Requisicoes requisicoes = Requisicoes.getInstancia();

        // Procura por uma requisição ativa associada a esta mesa
        Requisicao requisicao = requisicoes.listar().stream()
                .filter(req -> req.getPedido().getIdPedido() == this.getIdPedido())
                .findFirst()
                .orElse(null);

        return requisicao; // Retorna a requisição encontrada
    }

}
