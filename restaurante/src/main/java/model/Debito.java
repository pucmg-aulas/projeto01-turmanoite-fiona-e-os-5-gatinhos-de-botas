package model;

import interfaces.FormaDePagamento;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa a forma de pagamento Débito.
 */
public class Debito extends FormaDePagamento implements Serializable {

    private static final double DESCONTO_DEBITO = 0.014; // 1,4% representado em decimal
    private static final int PRAZO_RECEBIMENTO = 14; // 14 dias

    @Override
    public String getTipo() {
        return "Débito";
    }

    @Override
    public double getDesconto() {
        return DESCONTO_DEBITO * 100; // Convertendo para porcentagem
    }

    @Override
    public int getPrazoRecebimento() {
        return PRAZO_RECEBIMENTO;
    }

    @Override
    public String gerarNota() {
        LocalDate dataPagamento = getDataPagamento();
        String dataFormatada = dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return String.format("Pagamento via Cartão de Débito:\n" +
                "Desconto de %.2f%%\n" +
                "Valor final: R$ %.2f\n" +
                "Data do pagamento: %s\n" +
                "Prazo de recebimento: %d dias.", getDesconto(), getValorFinal(), dataFormatada, getPrazoRecebimento());
    }

    @Override
    public void calcularValorFinal(double valorBase) {
        double desconto = valorBase * DESCONTO_DEBITO;
        this.valorFinal = valorBase - desconto;
    }
}
