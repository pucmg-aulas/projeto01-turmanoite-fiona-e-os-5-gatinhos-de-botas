/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import interfaces.*;

/**
 *
 * @author pedro
 */
public class Pix implements FormaDePagamento {

    @Override
    public String getTipo() {
        return "Pix";
    }

    @Override
    public double getDesconto() {
        return 1.45; // Representado em porcentagem
    }

    @Override
    public int getPrazoRecebimento() {
        return 0;
    }

    @Override
    public String gerarNota() {
        return "Pagamento via Pix: Desconto de 1,45% (máximo de R$10), recebimento imediato.";
    }
}
