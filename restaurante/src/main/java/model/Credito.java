package model;

import interfaces.FormaDePagamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa a forma de pagamento Crédito.
 */
public class Credito extends FormaDePagamento implements Serializable {

    private static final double DESCONTO_CREDITO = 0.031; // 3,1% representado em decimal
    private static final int PRAZO_RECEBIMENTO = 30; // 30 dias

    @Override
    public String getTipo() {
        return "Crédito";
    }

    @Override
    public double getDesconto() {
        return DESCONTO_CREDITO * 100; // Convertendo para porcentagem
    }

    @Override
    public int getPrazoRecebimento() {
        return PRAZO_RECEBIMENTO;
    }

    @Override
    public String gerarNota() {
        LocalDate dataPagamento = getDataPagamento();
        String dataFormatada = dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format("Pagamento via Cartão de Crédito:\n" +
                "Desconto de %.2f%%\n" +
                "Valor final: R$ %.2f\n" +
                "Data do pagamento: %s\n" +
                "Prazo de recebimento: %d dias.", getDesconto(), getValorFinal(), dataFormatada, getPrazoRecebimento());
    }

    @Override
    public void calcularValorFinal(double valorBase) {
        double desconto = valorBase * DESCONTO_CREDITO;
        this.valorFinal = valorBase - desconto;
    }
}
