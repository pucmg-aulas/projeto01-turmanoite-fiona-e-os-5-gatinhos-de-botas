
public class Cliente {
    private static int contadorCliente = 0;
    private int idCliente;
    private String nome;
    private int qtdPessoas;

    public Cliente(String nome, int qtdPessoas) {
        this.idCliente = ++contadorCliente;
        this.nome = nome;
        this.qtdPessoas = qtdPessoas;
    }

    public String getNome() {
        return this.nome;
    }

    public int getQtdPessoas() {
        return this.qtdPessoas;
    }
}