/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ItemProduto;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Pedidos {

    private List<ItemProduto> items;

    // Instância única da classe Mesas
    private static Pedidos instancia;
    
    // Método para obter a instância única da classe
    public static Pedidos getInstancia() {
        if (instancia == null) {
            instancia = new Pedidos();

        }
        return instancia;
    }

    // Construtor privado para evitar instanciamento externo
    private Pedidos() {
        this.items = new ArrayList<>();
    }

    
    
    public List getItems(){
        return items;
    }
    
    

    public void adicionar(ItemProduto Item) {
        if (Item != null) {
            items.add(Item);
        } else {
            throw new IllegalArgumentException("O item não pode ser nulo.");
        }
    }

    public void removerMesa(ItemProduto m) {
        items.remove(m);
    }

    public ItemProduto obter(int idItemProduto) {
        for (ItemProduto item : items) {
            if (item.getIdItemProduto() == idItemProduto) {
                return item;
            }
        }
        return null;
    }

    public List<ItemProduto> listar() {
        return new ArrayList<>(items);
    }

    @Override
    public String toString() {
        return "Pedido{"
                + "items=" + items
                + '}';
    }
}
