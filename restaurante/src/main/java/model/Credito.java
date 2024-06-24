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
public class Credito implements FormaDePagamento {

    @Override
    public String getTipo() {
        return "Crédito";
    }

    @Override
    public double getDesconto() {
        return 3.1;
    }

    @Override
    public int getPrazoRecebimento() {
        return 30;
    }

    @Override
    public String gerarNota() {
        return "Pagamento via Crédito: Desconto de 3,1%, recebimento em 30 dias.";
    }
}
