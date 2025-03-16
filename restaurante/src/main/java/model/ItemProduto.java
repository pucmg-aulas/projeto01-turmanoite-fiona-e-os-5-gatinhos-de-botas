/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author pedro
 */
public class ItemProduto implements Serializable{
    private static int contadorItemProduto;
    private int idItemProduto;
    private int qnt;
    private Produto produto;
    private double valorTotal;
    
public ItemProduto(Produto p, int quantidade) {
    this.idItemProduto = ++contadorItemProduto;
        this.qnt = quantidade;
        this.produto = p;
        this.valorTotal = produto.getPreco() * quantidade;
    }

    public int getIdItemProduto() {
        return idItemProduto;
    }
    
    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQnt() {
        return qnt;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    
    
}
