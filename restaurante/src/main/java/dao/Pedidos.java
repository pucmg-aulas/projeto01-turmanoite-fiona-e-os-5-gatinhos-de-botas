/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Pedido;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Pedidos {

    private List<Pedido> pedidos;

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
        this.pedidos = new ArrayList<>();
    }

    
    
    public List getItems(){
        return pedidos;
    }
    
    

    public void adicionar(Pedido pedido) {
        if (pedido != null) {
            pedidos.add(pedido);
        } else {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
    }

    public void removerMesa(Pedido m) {
        pedidos.remove(m);
    }

    public Pedido obter(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public String toString() {
        return "Pedidos{"
                + "pedidos=" + pedidos
                + '}';
    }
}
