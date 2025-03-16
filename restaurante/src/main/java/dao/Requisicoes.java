/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import model.*;

import java.util.*;

public class Requisicoes extends AbstractDAO implements Serializable{

    private List<Requisicao> requisicoes;
    private final String path = "./src/main/java/data/Requisicoes.dat";
    
    // Instância única da classe Requisicoes
    private static Requisicoes instancia;

    // Construtor privado para evitar instanciamento externo
    private Requisicoes() {
        this.requisicoes = new ArrayList<>();
        carregaReq();
    }

    // Método para obter a instância única da classe
    public static Requisicoes getInstancia() {
        if (instancia == null) {
            instancia = new Requisicoes();
        }
        return instancia;
    }

    public void adicionar(Requisicao requisicao) {
        if (requisicao != null) {
            requisicoes.add(requisicao);
            grava();
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
    
    public void grava(){
        super.grava(path, requisicoes);
    }

    private void carregaReq(){
        this.requisicoes = super.leitura(path);
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
    public void reset() {
        this.requisicoes.clear(); // Limpa a lista de requisições
        this.grava(); // Salva o estado vazio para persistência
    }
    
}