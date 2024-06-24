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
public class Dinheiro extends FormaDePagamento {

    @Override
    public String getTipo() {
        return "Dinheiro";
    }

    @Override
    public double getDesconto() {
        return 0.0;
    }

    @Override
    public int getPrazoRecebimento() {
        return 0;
    }

    @Override
    public String gerarNota() {
        return "Pagamento em Dinheiro: Sem desconto, recebimento imediato.";
    }
        @Override public void calcularValorFinal(){
        
    }

    
}
