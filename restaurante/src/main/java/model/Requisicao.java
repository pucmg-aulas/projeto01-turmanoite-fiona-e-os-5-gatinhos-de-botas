package model;

import dao.*;
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class Requisicao implements Serializable {

    private int idRequisicao;
    private Cliente cliente;
    private Mesa mesa;
    private Pedido pedido;
    private boolean status;

    public Requisicao(Cliente cliente) {
        this.idRequisicao = IdAutoReq.getInstancia().getProximoId();
        this.cliente = cliente;
        this.status = false;
        this.pedido = new Pedido(this);
        Pedidos pedidos = Pedidos.getInstancia();
        pedidos.adicionar(pedido);
    }

    public int getIdRequisicao() {
        return idRequisicao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public boolean getStatus() {
        return status;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Requisicao{"
                + "idRequisicao=" + idRequisicao
                + ", cliente=" + cliente
                + ", mesa=" + mesa
                + ", pedido=" + pedido
                + ", status=" + status
                + '}';
    }
}
