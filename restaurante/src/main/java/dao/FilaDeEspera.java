/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import model.*;

import java.util.*;

public class FilaDeEspera extends AbstractDAO implements Serializable{

    private List<Requisicao> requisicoes;
    
    
    // Instância única da classe Requisicoes
    private static FilaDeEspera instancia;

    // Construtor privado para evitar instanciamento externo
    private FilaDeEspera() {
        this.requisicoes = new ArrayList<>();
        
    }

    // Método para obter a instância única da classe
    public static FilaDeEspera getInstancia() {
        if (instancia == null) {
            instancia = new FilaDeEspera();
        }
        return instancia;
    }

    public void adicionar(Requisicao requisicao) {
        if (requisicao != null) {
            requisicoes.add(requisicao);
        } else {
            throw new IllegalArgumentException("A requisição não pode ser nula.");
        }
    }

    

    public void removerReq(Requisicao r) {

        requisicoes.remove(r);

    }


    public List<Requisicao> listar() {
        return new ArrayList<>(requisicoes);
    }
    
    
    
    public Requisicao obter(int idRequisicao) {
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getIdRequisicao() == idRequisicao) {
                return requisicao;
            }
        }
        return null;
    }

   
    @Override
    public String toString() {
        return "Requisicoes{"
                + "requisicoes=" + requisicoes
                + '}';
    }
}