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
public class Pedido implements Serializable{
    
   // private ArrayList<ItemProduto> produtos = new ArrayList<ItemProduto>();
    private static double TAXA = 1.1;
    private static int contadorPedidos = 0;
    private int idPedido;
    private double totalProdutos;
    private int ativo = 1;

    public Pedido() {
        this.idPedido = ++contadorPedidos;

    }
    
}
