/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FilaDeEspera;
import dao.Requisicoes;

import model.*;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class CancelarRequisicaoController {
    private Requisicoes requisicoesModel;
    private FilaDeEspera filaDeEsperaDAO;
    private int idReq;
    // fazer a view
    
    public CancelarRequisicaoController(){
    this.requisicoesModel = Requisicoes.getInstancia();
    this.filaDeEsperaDAO = FilaDeEspera.getInstancia();
    // instanciar nova view e chamar os metodos nela, pedindo ao usuario a variavel
    // idReq e removendo ela tanto da fila de espera quanto da lista de req.
        
    }
    
    public Requisicao getRequisicaoInRequisicoes(){
       return requisicoesModel.obter(idReq); 
    }
    public Requisicao getRequisicaoInFiladeEspera(){
        return filaDeEsperaDAO.obter(idReq);
    }
    public void deleteRequisicaoInRequisicoes(){
        requisicoesModel.removerReq(getRequisicaoInRequisicoes());
    }
    public void deleteRequisicaoInFilaDeEspera(){
        filaDeEsperaDAO.removerReq(getRequisicaoInFiladeEspera());
    }
}
