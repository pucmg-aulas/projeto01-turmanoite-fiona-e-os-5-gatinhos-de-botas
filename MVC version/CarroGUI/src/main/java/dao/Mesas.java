/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Mesa;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Mesas {

    private List<Mesa> mesas;

    // Instância única da classe Mesas
    private static Mesas instancia;

    // Construtor privado para evitar instanciamento externo
    private Mesas() {
        this.mesas = new ArrayList<>();
    }

    public void iniciaMesas() {
        this.adicionar(new Mesa(4));
        this.adicionar(new Mesa(4));
        this.adicionar(new Mesa(4));
        this.adicionar(new Mesa(4));
        this.adicionar(new Mesa(6));
        this.adicionar(new Mesa(6));
        this.adicionar(new Mesa(6));
        this.adicionar(new Mesa(6));
        this.adicionar(new Mesa(8));
        this.adicionar(new Mesa(8));

    }
    
    public List getMesas(){
        return mesas;
    }
    
    // Método para obter a instância única da classe
    public static Mesas getInstancia() {
        if (instancia == null) {
            instancia = new Mesas();

        }
        return instancia;
    }

    public void adicionar(Mesa mesa) {
        if (mesa != null) {
            mesas.add(mesa);
        } else {
            throw new IllegalArgumentException("A mesa não pode ser nula.");
        }
    }

    public void removerMesa(Mesa m) {
        mesas.remove(m);
    }

    public Mesa obter(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getIdMesa() == idMesa) {
                return mesa;
            }
        }
        return null;
    }

    public List<Mesa> listar() {
        return new ArrayList<>(mesas);
    }

    @Override
    public String toString() {
        return "Mesas{"
                + "mesas=" + mesas
                + '}';
    }
}
