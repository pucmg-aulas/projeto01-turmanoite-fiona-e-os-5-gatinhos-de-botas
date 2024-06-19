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
public class Mesa {

    private static int contadorMesa = 0;
    private int idMesa;
    private int capacidade;
    private boolean status;

    public Mesa(int capacidade) {
        this.idMesa = ++contadorMesa;
        this.capacidade = capacidade;
        this.status = true;

    }

    

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean getStatus() {
        return status;

    }
}
