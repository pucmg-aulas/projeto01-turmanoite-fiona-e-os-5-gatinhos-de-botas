
public class Cliente {
    private static int idCliente;
    private String nome;
    private int qtdPessoas;

    public Cliente(String nome, int qtdPessoas){
        Cliente.idCliente += 1;
        this.nome = nome;
        this.qtdPessoas = qtdPessoas;
    }

    public int getQtdPessoas(){
        return this.qtdPessoas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }
    
}
