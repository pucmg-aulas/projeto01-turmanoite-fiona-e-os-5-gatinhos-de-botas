/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Requisicoes;
import dao.Mesas;

import model.*;
import view.AddReqView;
import view.FzrPedidoMenuView;
/**
 *
 * @author Thiago Cury
 */



public class FzrPedidoMenuController {
    private Requisicoes requisicoes;
    private Mesas mesas;
    private FzrPedidoMenuView view;
    
//    construtor
    public FzrPedidoMenuController() {
        this.requisicoes = Requisicoes.getInstancia();
        this.view = new FzrPedidoMenuView();
        
        view.setVisible(true);
    }

    public void EscolhendoAMesa(){
        
    }
    
}
