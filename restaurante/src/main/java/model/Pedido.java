package model;

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
    private double desconto;
    private double valorFinal;
    private int prazo;

    public Pedido() {
        this.idPedido = ++contadorPedidos;
        this.produtos = new ArrayList<>();
        this.totalProdutos = 0.0;
        this.ativo = 1; // Pedido ativo por padrão
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
        recalcularTotal();
    }

    public void removerItem(ItemProduto item) {
        if (this.produtos.remove(item)) {
            recalcularTotal();
        }
    }

    private void recalcularTotal() {
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
        this.ativo = 0;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public int getPrazo() {
        return prazo;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " - Total: R$ " + totalProdutos + " (Ativo: " + (ativo == 1 ? "Sim" : "Não") + ")";
    }
}
