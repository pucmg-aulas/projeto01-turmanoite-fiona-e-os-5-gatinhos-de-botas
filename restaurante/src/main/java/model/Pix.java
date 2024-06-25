package model;

import interfaces.FormaDePagamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa a forma de pagamento Pix.
 */
public class Pix extends FormaDePagamento implements Serializable {

    private static final double DESCONTO_MAXIMO = 0.0145; // 1,45% representado em decimal
    private static final double LIMITE_DESCONTO = 10.0; // R$ 10.00

    @Override
    public String getTipo() {
        return "Pix";
    }

    @Override
    public double getDesconto() {
        double desconto = DESCONTO_MAXIMO * 100; // Convertendo para porcentagem
        return Math.min(desconto, LIMITE_DESCONTO); // Limitando o desconto ao máximo de R$ 10.00
    }

    @Override
    public int getPrazoRecebimento() {
        return 0; // Recebimento imediato
    }

    @Override
    public String gerarNota() {
        LocalDate dataPagamento = getDataPagamento();
        String dataFormatada = dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format("Pagamento via Pix:\n" +
                "Desconto de %.2f%% (máximo de R$ %.2f)\n" +
                "Valor final: R$ %.2f\n" +
                "Data do pagamento: %s\n" +
                "Recebimento imediato.", getDesconto(), LIMITE_DESCONTO, getValorFinal(), dataFormatada);
    }

    @Override
    public void calcularValorFinal(double valorBase) {
        double desconto = valorBase * DESCONTO_MAXIMO;
        desconto = Math.min(desconto, LIMITE_DESCONTO); // Limitando o desconto ao máximo de R$ 10.00
        this.valorFinal = valorBase - desconto;
    }
}
