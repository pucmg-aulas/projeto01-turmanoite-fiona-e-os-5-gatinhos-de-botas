package model;

import interfaces.FormaDePagamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa a forma de pagamento Dinheiro.
 */
public class Dinheiro extends FormaDePagamento implements Serializable {

    @Override
    public String getTipo() {
        return "Dinheiro";
    }

    @Override
    public double getDesconto() {
        return 0.0; // No caso de dinheiro, não há desconto
    }

    @Override
    public int getPrazoRecebimento() {
        return 0; // Recebimento imediato
    }

    @Override
    public String gerarNota() {
        LocalDate dataPagamento = getDataPagamento();
        String dataFormatada = dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format("Pagamento em Dinheiro:\n" +
                "Valor final: R$ %.2f\n" +
                "Data do pagamento: %s\n" +
                "Recebimento imediato.", getValorFinal(), dataFormatada);
    }

    @Override
    public void calcularValorFinal(double valorBase) {
        this.valorFinal = valorBase; // Valor final é igual ao valor base para pagamento em dinheiro
    }
}
