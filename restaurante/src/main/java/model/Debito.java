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
public class Debito implements FormaDePagamento {

    @Override
    public String getTipo() {
        return "Débito";
    }

    @Override
    public double getDesconto() {
        return 1.4;
    }

    @Override
    public int getPrazoRecebimento() {
        return 14;
    }

    @Override
    public String gerarNota() {
        return "Pagamento via Débito: Desconto de 1,4%, recebimento em 14 dias.";
    }
}
