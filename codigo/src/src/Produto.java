public class Produto {
    private static int contadorProdutos = 0;
    private String nome;
    private int idProduto;
    private double preco;

    public Produto(String nome, double preco) {
        this.idProduto = ++contadorProdutos;
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreço() {
        return preco;
    }
}
