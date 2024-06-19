/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pedro
 */
public class Cliente {
    
    private static int contadorClientes = 0;
    private int idCliente;
    private String nome;
    private int qtdPessoas;

    public String getNome() {
        return nome;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public Cliente(String nome, int qtdPessoas) {
        this.idCliente = ++contadorClientes;
        this.nome = nome;
        this.qtdPessoas = qtdPessoas;
    }
    
}
