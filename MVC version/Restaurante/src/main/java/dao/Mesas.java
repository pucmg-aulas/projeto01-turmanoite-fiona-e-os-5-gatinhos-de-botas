/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Mesa;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Mesas extends AbstractDAO{

    private List<Mesa> mesas;
    private final String path = "./src/main/java/data/Mesas.dat";

    // Instância única da classe Mesas
    private static Mesas instancia;

    // Construtor privado para evitar instanciamento externo
    private Mesas() {
        this.mesas = new ArrayList<>();
        carregaMesas();
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
    private void grava(){
        super.grava(path, mesas);
    }
    
    
    public void adicionar(Mesa mesa) {
        if (mesa != null) {
            mesas.add(mesa);
            grava(path, mesas);
        } else {
            throw new IllegalArgumentException("A mesa não pode ser nula.");
        }
    }

    public void removerMesa(Mesa m) {
        mesas.remove(m);
        grava();
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

    private void carregaMesas() {
        this.mesas = super.leitura(path);
    }
}
