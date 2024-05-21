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

<<<<<<< HEAD:codigo/src/Produto.java
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProduto() {
        return this.idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }    
=======
    public int getIdProduto() {
        return idProduto;
    }
>>>>>>> 9f5ad96ba268c4d4f93c005c53bea8bd507a16b4:codigo/src/src/Produto.java
}
