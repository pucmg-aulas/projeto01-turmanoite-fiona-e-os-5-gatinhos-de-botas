/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Requisicao;

/**
 *
 * @author ticok
 */
public class FilaDeEspera extends AbstractDAO{
    private List<Requisicao> fila;

    // Instância única da classe Requisicoes
    private static FilaDeEspera instancia;

    // Construtor privado para evitar instanciamento externo
    private FilaDeEspera() {
        this.fila = new ArrayList<>();
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
            fila.add(requisicao);
        } else {
            throw new IllegalArgumentException("A requisição não pode ser nula.");
        }
    }

    

    public void removerReq(Requisicao r) {

        fila.remove(r);

    }

    public Requisicao obter(int idRequisicao) {
        for (Requisicao requisicao : fila) {
            if (requisicao.getIdRequisicao() == idRequisicao) {
                return requisicao;
            }
        }
        return null;
    }

    public List<Requisicao> listar() {
        return new ArrayList<>(fila);
    }

    @Override
    public String toString() {
        return "Requisicoes{" + "requisicoes=" + fila + '}';
    }
}
