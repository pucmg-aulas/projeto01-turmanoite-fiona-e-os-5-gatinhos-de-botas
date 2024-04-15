import java.util.Date;

public class Cliente {
    private int id;
    private String nome;
    private int qntPessoas;

    public Cliente(int id, String nome, int qntPessoas) {
        this.id = id;
        this.nome = nome;
        this.qntPessoas = qntPessoas;
    }
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getQntPessoas() {
        return qntPessoas;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQntPessoas(int qntPessoas) {
        this.qntPessoas = qntPessoas;
    }
}
