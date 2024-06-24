package interfaces;

import java.time.LocalDate;

/**
 * Classe abstrata que define a estrutura básica para formas de pagamento.
 */
public abstract class FormaDePagamento {

    protected double valorFinal;
    protected LocalDate dataPagamento;

    // Método abstrato para retornar o tipo de pagamento
    public abstract String getTipo();

    // Método abstrato para retornar o desconto aplicado
    public abstract double getDesconto();

    // Método abstrato para retornar o prazo de recebimento
    public abstract int getPrazoRecebimento();

    // Método abstrato para gerar a nota do pagamento
    public abstract String gerarNota();

    // Método abstrato para calcular o valor final com base no valor base
    public abstract void calcularValorFinal();

    // Retorna o valor final calculado
    public double getValorFinal() {
        return valorFinal;
    }

    // Define a data do pagamento
    public void setDataPagamento() {
        this.dataPagamento = LocalDate.now();
    }


    // Retorna a data do pagamento
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
}
