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
public class Produto implements Serializable{
    
    private static int contadorProdutos = 0;
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public double getPreco() {
        return preco;
    }
    private int idProduto;
    private double preco;

    public Produto(String nome, double preco) {
        this.idProduto = ++contadorProdutos;
        this.nome = nome;
        this.preco = preco;
    }
    
}
