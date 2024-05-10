
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNome'");
    }
}
